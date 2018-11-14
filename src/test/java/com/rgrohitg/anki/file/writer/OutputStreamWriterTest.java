package com.rgrohitg.anki.file.writer;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(BlockJUnit4ClassRunner.class)
public class OutputStreamWriterTest {

	private static final String USER_SESSION_FILE_SUCCESS = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\data\\user1QA1.txt";

	@Test
	public void givenUserGameSession_whenWriteMethodInvoked_thenDataDataIsSaved() {

		GameDataStreamWriter gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(TestUtils.getDefaultUserGame(), USER_SESSION_FILE_SUCCESS);

		File userSessionFile = new File(USER_SESSION_FILE_SUCCESS);
		assertTrue(userSessionFile.exists());
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenUserGameSessionAndEmptyPath_whenWriteMethodInvoked_IllegalArgumentExceptionRaised() {
		GameDataStreamWriter gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(TestUtils.getDefaultUserGame(), null);
	}

}
