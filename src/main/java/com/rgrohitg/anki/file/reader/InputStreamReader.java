package com.rgrohitg.anki.file.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.rgrohitg.anki.model.UserGame;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputStreamReader implements Reader<UserGame> {

	@Override
	public UserGame read(String path) throws IOException {
		UserGame loadedState = null;
		File file = new File(path);
		try (FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream is = new ObjectInputStream(fileInputStream);) {
			loadedState = (UserGame) is.readObject();

		} catch (ClassNotFoundException e) {
			log.error("Unable to load class " + e);
		}

		return loadedState;
	}

}