import json

# Define the format strings for various check types
CHECK_FORMATS = {
    "uniqueTuple": "duplicate_count({column}) <= 0",
    "columns": "column_values_in_set({column}) matches {regex}",
}

def generate_sodacl_checks(json_input):
    """
    Convert a JSON input to SodaCL check statements.
    
    Parameters:
    json_input (list): The input JSON list containing sheet details.

    Returns:
    str: The generated SodaCL check statements.
    
    Raises:
    ValueError: If an unsupported check type is encountered.
    """
    sodacl_output = []

    # Iterate over each spec in the input list
    for spec in json_input:
        details = spec.get("details", {})
        sheets = details.get("sheets", [])

        # Process each sheet
        for sheet in sheets:
            table_name = sheet.get("table_name")
            sodacl_output.append(f"checks for {table_name}:")

            # Process uniqueTuple
            unique_tuple = sheet.get("uniqueTuple", [])
            for column in unique_tuple:
                sodacl_output.append(f"  - {CHECK_FORMATS['uniqueTuple'].format(column=column)}")

            # Process columns
            columns = sheet.get("columns", [])
            for column in columns:
                column_name = column.get("source_col_name")
                is_required = column.get("is_required")
                valid_regex = column.get("valid_regex")
                
                if is_required == "Y":
                    sodacl_output.append(f"  - missing_count({column_name}) = 0")
                
                if valid_regex:
                    sodacl_output.append(f"  - {CHECK_FORMATS['columns'].format(column=column_name, regex=valid_regex)}")

    # Return the generated SodaCL checks as a single string
    return "\n".join(sodacl_output)

def save_sodacl_checks_to_file(sodacl_checks, filename):
    """
    Save the generated SodaCL checks to a file.
    
    Parameters:
    sodacl_checks (str): The generated SodaCL checks.
    filename (str): The name of the file to save the checks.
    """
    with open(filename, 'w') as file:
        file.write(sodacl_checks)


# Load JSON input
json_data = json.loads(json_input)
# Generate SodaCL checks
sodacl_checks = generate_sodacl_checks(json_data)
# Save the generated checks to a file
save_sodacl_checks_to_file(sodacl_checks, 'sodacl_checks.txt')

# Print the generated checks
print(sodacl_checks)
