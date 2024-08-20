import requests
from datetime import datetime, timedelta
import csv
import json

class BitbucketPRAnalyzer:
    def __init__(self, bitbucket_access_key, bitbucket_cluster_url, project_name, repo_name):
        self.bitbucket_access_key = bitbucket_access_key
        self.bitbucket_cluster_url = bitbucket_cluster_url
        self.project_name = project_name
        self.repo_name = repo_name
        self.base_url = f"{bitbucket_cluster_url}/projects/{project_name}/repos/{repo_name}/pull-requests"
        self.headers = {
            'Authorization': f'Bearer {self.bitbucket_access_key}',
            'Content-Type': 'application/json'
        }
    
    def fetch_pull_requests(self, days):
        since_date = datetime.now() - timedelta(days=days)
        since_str = since_date.strftime('%Y-%m-%dT%H:%M:%S.%f')[:-3] + 'Z'
        params = {
            'state': 'ALL',  # Check all PRs: open, merged, declined, etc.
            'fromDate': since_str
        }
        
        response = requests.get(self.base_url, headers=self.headers, params=params)
        response.raise_for_status()
        
        try:
            pr_data = response.json()
            return pr_data.get('values', [])
        except json.JSONDecodeError as e:
            print(f"Error decoding JSON response: {e}")
            return []
    
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
            # Check if PR was approved quickly (within 30 minutes)
            created_on = datetime.strptime(pr['createdDate'], '%Y-%m-%dT%H:%M:%S.%f%z')
            updated_on = datetime.strptime(pr['updatedDate'], '%Y-%m-%dT%H:%M:%S.%f%z')

            if pr['state'] == 'MERGED' and (updated_on - created_on).total_seconds() <= 1800:
                fast_approved += 1

            # Check if PR has no comments
            if pr['commentCount'] == 0:
                no_comments += 1

            # Check if PR has the "needs work" status
            if pr['state'] == 'OPEN' and any(participant['role'] == 'REVIEWER' and not participant.get('approved', True) for participant in pr['reviewers']):
                needs_work += 1

            # Check if PR was declined
            if pr['state'] == 'DECLINED':
                declined += 1

            # Accumulate total comments
            total_comments += pr['commentCount']

        pr_count = len(prs)
        
        # Calculating percentages
        return {
            "project_name": self.project_name,
            "repo_name": self.repo_name,
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
    bitbucket_cluster_url = "https://your_bitbucket_cluster_url"  # Your Bitbucket Cluster URL
    project_name = "your_project_name"  # Your Project Name
    repo_name = "your_repo_name"  # Your Repository Name
    days = 7  # You can set this to 7, 10, 30, etc.
    output_format = "json"  # Can be 'csv' or 'json'
    output_file = f"bitbucket_pr_analysis.{output_format}"

    analyzer = BitbucketPRAnalyzer(bitbucket_access_key, bitbucket_cluster_url, project_name, repo_name)
    results = analyzer.analyze_pull_requests(days)
    
    if results:
        if output_format == "csv":
            analyzer.output_to_csv(results, output_file)
        elif output_format == "json":
            analyzer.output_to_json(results, output_file)
        print(f"Results have been saved to {output_file}.")
    else:
        print(f"No PRs found in the last {days} days for the repository '{repo_name}'.")
