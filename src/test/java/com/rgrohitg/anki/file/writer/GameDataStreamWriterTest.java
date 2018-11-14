package com.rgrohitg.anki.file.writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class GameDataStreamWriterTest {
	private static final String USER_SESSION_FILE_SUCCESS = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\data\\user1QA1.txt";

	@Mock
	GameDataStreamWriter gameWriter;

	@Test
	public void givenUserGameSession_whenGameDataisStored_testCorrectBehaviour() {
		gameWriter = new GameDataStreamWriter(new OutputStreamWriter());
		gameWriter.write(TestUtils.getDefaultUserGame(), USER_SESSION_FILE_SUCCESS);
		OutputStreamWriter outputStreamWriter = Mockito.mock(OutputStreamWriter.class);
		// Mockito.doNothing().when(gameWriter.write(Mockito.any(), Mockito.any());
		Mockito.verify(outputStreamWriter).write(TestUtils.getDefaultUserGame(), USER_SESSION_FILE_SUCCESS);
		;

	}
}
