import json

# Define the format strings for various check types
CHECK_FORMATS = {
    "row_count": "row_count between {min} and {max}",
    "duplicate_count": "duplicate_count({column}) <= {max}",
    "column_value_in_set": "column_values_in_set({column}) in {values}"
}

def generate_sodacl_checks(json_input):
    """
    Convert a JSON input to SodaCL check statements.
    
    Parameters:
    json_input (dict): The input JSON containing the table name and checks.

    Returns:
    str: The generated SodaCL check statements.
    
    Raises:
    ValueError: If an unsupported check type is encountered.
    """
    sodacl_output = []

    # Extract table name
    table_name = json_input.get("table_name")
    # Extract checks
    checks = json_input.get("checks", [])

    # Add table name to the output
    if table_name:
        sodacl_output.append(f"checks for {table_name}:")

    # Generate checks
    for check in checks:
        check_type = check.get("type")

        if check_type in CHECK_FORMATS:
            # Get the format string for the check type
            format_string = CHECK_FORMATS[check_type]
            # Format the check using the format string and check details
            if check_type == "column_value_in_set":
                check["values"] = ", ".join(check["values"])  # Convert list to comma-separated string
            formatted_check = format_string.format(**check)
            sodacl_output.append(f"  - {formatted_check}")
        else:
            raise ValueError(f"Unsupported check type: {check_type}")

    # Return the generated SodaCL checks as a single string
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

# Load JSON input
json_data = json.loads(json_input)
# Generate SodaCL checks
sodacl_checks = generate_sodacl_checks(json_data)
# Print the generated checks
print(sodacl_checks)
