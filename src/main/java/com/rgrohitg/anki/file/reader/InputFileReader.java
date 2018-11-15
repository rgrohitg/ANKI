package com.rgrohitg.anki.file.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputFileReader implements Reader<List<String>> {

	@Override
	public List<String> read(String path) throws IOException {
		List<String> records = new ArrayList<>();

		if (path == null || path.isEmpty() || !Utils.isFileExist(path)) {
			log.error("Unable to read file :" + path);
			throw new IllegalArgumentException();
		}

		String line;
		try (FileReader fileReader = new FileReader(path); BufferedReader parser = new BufferedReader(fileReader);) {
			while ((line = parser.readLine()) != null) {
				records.add(line);
			}
		} catch (Exception e) {
			log.error("Exception occurred trying to read '%s'.", path + e);
		}

		return records;

	}

}
