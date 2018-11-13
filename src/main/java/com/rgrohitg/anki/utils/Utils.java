package com.rgrohitg.anki.utils;

import java.io.File;

public class Utils {

	public static File file;

//	static Predicate<String> isNotEmpty = s -> !s.isEmpty() && s != null;

	public static boolean isFileExist(String name) {
		file = new File(name);

		if (file.exists()) {
			return true;
		}
		return false;
	}
}
