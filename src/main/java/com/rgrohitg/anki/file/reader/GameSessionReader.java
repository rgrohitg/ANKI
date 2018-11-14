package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import com.rgrohitg.anki.model.UserGame;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameSessionReader extends AbstractFileReader<List<UserGame>> {

	public GameSessionReader(Reader<InputStream> reader) {
		super(reader);
	}

	@Override
	public InputStream read(String path) throws IOException {
		return reader.read(path);
	}

	@SuppressWarnings("unchecked")
	@Override
	List<UserGame> readFromInputStream(InputStream inputStream) throws IOException {

		List<UserGame> loadedState = null;

		try (ObjectInputStream is = new ObjectInputStream(inputStream)) {
			loadedState = (List<UserGame>) (is.readObject());
		} catch (ClassNotFoundException e) {
			log.error("Unable to find the class " + e);
		}

		return loadedState;
	}
}
