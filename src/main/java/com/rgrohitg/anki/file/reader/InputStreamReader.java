package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.rgrohitg.anki.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputStreamReader implements Reader<InputStream> {

	@Override
	public InputStream read(String path) throws IOException {

		if (path == null || path.isEmpty() || !Utils.isFileExist(path)) {
			log.error("Unable to read file :" + path);
			throw new IllegalArgumentException();
		}
//		File file = new File(path);
//
//		InputStream inputStream = new FileInputStream(file);
//		return inputStream;

		try (Stream<String> stream = Files.lines(Paths.get(path))) {

			return (InputStream) stream;

		} catch (IOException e) {
			log.error("");
		}
		return null;

	}

}
