package com.rgrohitg.anki.file.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.model.Card;

@RunWith(BlockJUnit4ClassRunner.class)
public class CardReaderTest {

	private static final String EXPECTED_RESULT = "What enzyme breaks down sugars mouth and digestive tract?|Amylase";
	private static final int THREE = 3;
	private static final String FILE_PATH = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\src\\test\\resources\\Cards_1.txt";
	private static final String INCORRECT_ILE_PATH = "src/test/resources/Cards_XYZ.txt";

	@Test
	public void givenInputStream_whenReadFromInputStreamInvoked_ListOfCardsAreReturned() throws IOException {
		CardsReader cardsReader = new CardsReader(new InputFileReader());
		List<String> inputStream = cardsReader.read(FILE_PATH);
		Map<Integer, Card> cards = cardsReader.loadData(inputStream);
		assertNotNull(inputStream);
		assertNotNull(cards);
		assertEquals(EXPECTED_RESULT, inputStream.get(1));
		assertEquals(THREE, cards.size());
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
