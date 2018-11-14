package com.rgrohitg.anki.utils;

import java.io.File;
import java.nio.file.Paths;

public class Utils {

	static File file;

	private Utils() {
	}

	public static boolean isFileExist(String name) {
		file = new File(name);
		return file.exists();
	}

	public static boolean isDirectoryExist(String name) {
		return Paths.get(name).toFile().isDirectory();
	}

	public static void createDirectory(String name) {
		file = new File(name);
		file.mkdirs();
	}

}
