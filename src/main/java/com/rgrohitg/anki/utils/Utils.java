package com.rgrohitg.anki.utils;

import java.io.File;
import java.nio.file.Paths;

public class Utils {

	static File file;

	private Utils() {
	}

	public static boolean isFileExist(String name) {
		return Paths.get(name).toFile().exists();
	}
}
