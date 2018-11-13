package com.rgrohitg.anki.file.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.service.GameManager;

import lombok.Cleanup;

public class OutputStreamWriter implements Writer<UserGame> {

	GameManager manager = GameManager.getManager();

	@Override
	public UserGame write(UserGame data) {

		try {
			@Cleanup
			FileOutputStream fileStream = new FileOutputStream(manager.USER_GAME_STATE);
			@Cleanup
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(data);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
