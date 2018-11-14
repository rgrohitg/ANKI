package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class InputStreamReaderTest {

	private static final String FILE_PATH = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\src\\test\\resources\\Cards_1.txt";

	@Test
	public void givenCorrectFilePath_whenIread_thenInputStreamShouldBeReturned() throws IOException {
		InputStreamReader reader = new InputStreamReader();
		InputStream inputStream = reader.read(FILE_PATH);
		Assert.assertNotNull(inputStream);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenWrongFilePath_whenIread_thenIllegalArgumentExceptionIsThrown() throws IOException {
		InputStreamReader reader = new InputStreamReader();
		reader.read(null);
	}
}
