package com.rgrohitg.anki.file.reader;

import com.rgrohitg.anki.service.Constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReaderFactory {

	public static Reader getReader(String readMode) {
		switch (readMode) {
		case Constants.JAVA:
			return new InputStreamReader();
		case Constants.FILE:
			return new InputFileReader();
		default:
			return new InputFileReader();
		}

	}
}
