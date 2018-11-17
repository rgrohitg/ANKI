package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class InputStreamReaderTest {

	private static final String CORRECT_ILE_PATH = "src/test/resources/Cards_1.txt";
	private static final String INCORRECT_ILE_PATH = "src/test/resources/Cards_XYZ.txt";

	@Test
	public void givenCorrectFilePath_whenIread_thenInputStreamShouldBeReturned() throws IOException {
		InputFileReader reader = new InputFileReader();
		List<String> result = reader.read(CORRECT_ILE_PATH);
		Assert.assertNotNull(result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenNullPath_whenIread_thenIllegalArgumentExceptionIsThrown() throws IOException {
		InputFileReader reader = new InputFileReader();
		reader.read(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenWrongFilePath_whenIread_thenIllegalArgumentExceptionIsThrown() throws IOException {
		InputFileReader reader = new InputFileReader();
		reader.read(INCORRECT_ILE_PATH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenEmptyFilePath_whenIread_thenIllegalArgumentExceptionIsThrown() throws IOException {
		InputFileReader reader = new InputFileReader();
		reader.read("");
	}
}
