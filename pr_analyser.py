import requests
from datetime import datetime, timedelta
import csv
import json

class BitbucketPRAnalyzer:
    def __init__(self, bitbucket_access_key, workspace, repo_slug, project_name):
        self.bitbucket_access_key = bitbucket_access_key
        self.workspace = workspace
        self.repo_slug = repo_slug
        self.project_name = project_name
        self.base_url = f"https://api.bitbucket.org/2.0/repositories/{workspace}/{repo_slug}/pullrequests"
        self.headers = {
            'Authorization': f'Bearer {self.bitbucket_access_key}'
        }
    
    def fetch_pull_requests(self, days):
        since_date = datetime.now() - timedelta(days=days)
        since_str = since_date.strftime('%Y-%m-%dT%H:%M:%SZ')
        params = {
            'state': 'ALL',  # We want to check all PRs, including open, merged, declined, etc.
            'q': f'updated_on >= {since_str}'
        }
        
        response = requests.get(self.base_url, headers=self.headers, params=params)
        response.raise_for_status()
        return response.json()['values']
    
    def analyze_pull_requests(self, days):
        prs = self.fetch_pull_requests(days)
        if not prs:
            return None

        fast_approved = 0
        no_comments = 0
        needs_work = 0
        declined = 0
        total_comments = 0

        for pr in prs:
            # Check if PR was approved quickly (let's say within 30 minutes)
            if pr['state'] == 'MERGED' and pr['created_on'] == pr['updated_on']:
                fast_approved += 1

            # Check if PR has no comments
            if pr['comment_count'] == 0:
                no_comments += 1

            # Check if PR has the "needs work" status
            if pr['state'] == 'OPEN' and any(participant['role'] == 'REVIEWER' and participant['approved'] is False for participant in pr['participants']):
                needs_work += 1

            # Check if PR was declined
            if pr['state'] == 'DECLINED':
                declined += 1

            # Accumulate total comments
            total_comments += pr['comment_count']

        pr_count = len(prs)
        
        # Calculating percentages
        return {
            "project_name": self.project_name,
            "repo_name": self.repo_slug,
            "duration_days": days,
            "total_prs": pr_count,
            "fast_approved": fast_approved,
            "no_comments": no_comments,
            "needs_work": needs_work,
            "declined": declined,
            "total_comments": total_comments,
            "fast_approved_pct": (fast_approved / pr_count) * 100 if pr_count > 0 else 0,
            "no_comments_pct": (no_comments / pr_count) * 100 if pr_count > 0 else 0,
            "needs_work_pct": (needs_work / pr_count) * 100 if pr_count > 0 else 0,
            "declined_pct": (declined / pr_count) * 100 if pr_count > 0 else 0
        }

    def output_to_csv(self, results, output_file):
        with open(output_file, mode='w', newline='') as file:
            writer = csv.DictWriter(file, fieldnames=results.keys())
            writer.writeheader()
            writer.writerow(results)

    def output_to_json(self, results, output_file):
        with open(output_file, mode='w') as file:
            json.dump(results, file, indent=4)

if __name__ == "__main__":
    bitbucket_access_key = "your_bitbucket_access_key"  # Your Bitbucket Access Key
    workspace = "your_workspace"  # Your Bitbucket Workspace
    repo_slug = "your_repo_slug"  # Your Repository Slug
    project_name = "your_project_name"  # Your Project Name
    days = 7  # You can set this to 7, 10, 30, etc.
    output_format = "json"  # Can be 'csv' or 'json'
    output_file = f"bitbucket_pr_analysis.{output_format}"

    analyzer = BitbucketPRAnalyzer(bitbucket_access_key, workspace, repo_slug, project_name)
    results = analyzer.analyze_pull_requests(days)
    
    if results:
        if output_format == "csv":
            analyzer.output_to_csv(results, output_file)
        elif output_format == "json":
            analyzer.output_to_json(results, output_file)
        print(f"Results have been saved to {output_file}.")
    else:
        print(f"No PRs found in the last {days} days for the repository '{repo_slug}'.")
