package com.rgrohitg.anki.file.reader;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StreamCorruptedException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.model.UserGame;

@RunWith(BlockJUnit4ClassRunner.class)
public class GameSessionReaderTest {

	private static final String SUCCESS_FILE_PATH = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\data\\session_reader_user1.txt";
	private static final String FAIL_FILE_PATH = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\data\\session_reader_wrong.data.txt";

	@Test
	public void givenListOfStrings_whenRead_UserGameObjectAreReturned() throws IOException {
		GameSessionReader gameSession = new GameSessionReader(new InputStreamReader());
		UserGame savedSession = gameSession.read(SUCCESS_FILE_PATH);
		assertNotNull(savedSession);
	}

	@Test(expected = StreamCorruptedException.class)
	public void givenSerializedObject_whenRead_UserGameObjectAreReturned() throws IOException {
		GameSessionReader gameSession = new GameSessionReader(new InputStreamReader());
		gameSession.read(FAIL_FILE_PATH);

	}
}
