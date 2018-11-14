package com.rgrohitg.anki.file.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.rgrohitg.anki.model.UserGame;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutputStreamWriter implements Writer<UserGame> {

	@Override
	public void write(UserGame data, String filePath) {

		if (data == null || filePath == null || filePath.isEmpty()) {
			log.error("Error while saving the user session !!");
			throw new IllegalArgumentException();
		}

		File file = new File(filePath);

		try (ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file))) {
			objectStream.writeObject(data);
		} catch (IOException ioe) {
			log.error("Unable to write the file :" + ioe);
		}
	}

}
