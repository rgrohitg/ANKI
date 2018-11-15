package com.rgrohitg.anki.file.writer;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class GameDataStreamWriterTest {
	private static final String USER_SESSION_FILE_SUCCESS = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\data\\user1QA1.txt"
			+ (System.currentTimeMillis());

	GameDataStreamWriter gameWriter;

	OutputStreamWriter outputWriter;

	File savedFile;

	@Before
	public void setUp() {
		savedFile = new File(USER_SESSION_FILE_SUCCESS);
		outputWriter = new OutputStreamWriter();
		gameWriter = new GameDataStreamWriter(outputWriter);
	}

	@Test
	public void givenUserGameSession_whenGameDataisStored_testCorrectBehaviour() {

		gameWriter.write(TestUtils.getDefaultUserGame(), USER_SESSION_FILE_SUCCESS);
		assertTrue(savedFile.exists());

	}
}
