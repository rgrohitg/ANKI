package com.rgrohitg.anki.file.reader;

import java.io.IOException;

import com.rgrohitg.anki.model.UserGame;

public class GameSessionReader extends AbstractFileReader<UserGame> {

	public GameSessionReader(Reader<UserGame> reader) {
		super(reader);
	}

	@Override
	public UserGame read(String path) throws IOException {
		return reader.read(path);
	}

}
