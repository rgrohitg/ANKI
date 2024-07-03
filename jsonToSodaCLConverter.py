import json

CHECK_FORMATS = {
    "row_count": "row_count between {min} and {max}",
    "duplicate_count": "duplicate_count({column}) <= {max}",
    "column_value_in_set": "column_values_in_set({column}) in {values}"
}

def generate_sodacl_checks(json_input):
    sodacl_output = []

    table_name = json_input.get("table_name")
    checks = json_input.get("checks", [])

    if table_name:
        sodacl_output.append(f"checks for {table_name}:")

    for check in checks:
        check_type = check.get("type")

        if check_type in CHECK_FORMATS:
            format_string = CHECK_FORMATS[check_type]
            formatted_check = format_string.format(**check)
            sodacl_output.append(f"  - {formatted_check}")
        else:
            raise ValueError(f"Unsupported check type: {check_type}")

    return "\n".join(sodacl_output)

# Example usage
json_input = '''
{
    "table_name": "customers",
    "checks": [
        {
            "type": "row_count",
            "min": 100,
            "max": 1000
        },
        {
            "type": "duplicate_count",
            "column": "customer_id",
            "max": 0
        },
        {
            "type": "column_value_in_set",
            "column": "status",
            "values": ["active", "inactive", "pending"]
        }
    ]
}
'''

json_data = json.loads(json_input)
sodacl_checks = generate_sodacl_checks(json_data)
print(sodacl_checks)
