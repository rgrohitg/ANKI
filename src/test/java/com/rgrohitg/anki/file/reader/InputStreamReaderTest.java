package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class InputStreamReaderTest {

	private static final String FILE_PATH = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\src\\test\\resources\\Cards_1.txt";

	@Test
	public void givenCorrectFilePath_whenIread_thenInputStreamShouldBeReturned() throws IOException {
		InputFileReader reader = new InputFileReader();
		List<String> result = reader.read(FILE_PATH);
		Assert.assertNotNull(result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenWrongFilePath_whenIread_thenIllegalArgumentExceptionIsThrown() throws IOException {
		InputFileReader reader = new InputFileReader();
		reader.read(null);
	}
}
