package com.rgrohitg.anki.file.writer;

import com.rgrohitg.anki.model.UserGame;

public class GameDataStreamWriter extends AbstractFileWriter<UserGame> {

	public GameDataStreamWriter(Writer<UserGame> writer) {
		super(writer);
	}

	@Override
	public UserGame write(UserGame data) {
		return writer.write(data);
	}

}
