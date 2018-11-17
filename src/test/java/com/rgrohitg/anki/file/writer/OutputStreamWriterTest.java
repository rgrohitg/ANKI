package com.rgrohitg.anki.file.writer;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(BlockJUnit4ClassRunner.class)
public class OutputStreamWriterTest {

	private static final String CORRECT_USER_SESSION_FILE = "src/test/resources/user1QA1.txt";
	private static final String INCORRECT_USER_SESSION_FILE = "src/test/resources/incorrect.txt";

	@Test
	public void givenUserGameSession_whenWriteMethodInvoked_thenDataDataIsSaved() {

		GameDataStreamWriter gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(TestUtils.getDefaultUserGame(), CORRECT_USER_SESSION_FILE);

		File userSessionFile = new File(CORRECT_USER_SESSION_FILE);
		assertTrue(userSessionFile.exists());
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenUserGameSessionAndEmptyPath_whenWriteMethodInvoked_IllegalArgumentExceptionRaised() {
		GameDataStreamWriter gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(TestUtils.getDefaultUserGame(), "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenUserGameSessionAndNullPath_whenWriteMethodInvoked_IllegalArgumentExceptionRaised() {
		GameDataStreamWriter gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(TestUtils.getDefaultUserGame(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenNullAndWrongPath_whenWriteMethodInvoked_IllegalArgumentExceptionRaised() {
		GameDataStreamWriter gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(null, INCORRECT_USER_SESSION_FILE);
	}

}
