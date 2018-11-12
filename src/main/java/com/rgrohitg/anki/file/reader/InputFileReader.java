package com.rgrohitg.anki.file.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

/**
 * Text file parser
 * 
 * @author rgroh
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class InputFileReader implements Reader<String> {

	private String fileName;

	@Override
	public List<String> read() throws FileNotFoundException, IOException {
		List<String> records = new ArrayList<String>();

		@Cleanup
		FileReader fileReader = new FileReader(fileName);
		@Cleanup
		BufferedReader parser = new BufferedReader(fileReader);

		if (canRead()) {
			String line;
			try {
				while ((line = parser.readLine()) != null) {
					records.add(line);
				}
			} catch (Exception e) {
				System.err.format("Exception occurred trying to read '%s'.", fileName);
				e.printStackTrace();
				return null;
			}
		}
		return records;
	}

	@Override
	public Boolean canRead() {
		return (fileName != null && !fileName.isEmpty());
	}

}
