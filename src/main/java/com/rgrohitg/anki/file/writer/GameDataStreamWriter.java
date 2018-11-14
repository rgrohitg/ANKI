package com.rgrohitg.anki.file.writer;

import com.rgrohitg.anki.model.UserGame;

public class GameDataStreamWriter extends AbstractFileWriter<UserGame> {

	public GameDataStreamWriter(Writer<UserGame> writer) {
		super(writer);
	}

	@Override
	public void write(UserGame data, String filePath) {
		writer.write(data, filePath);
	}

}
