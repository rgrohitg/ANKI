package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CardReaderTest {

	private static final String FILE_PATH = "C:\\Users\\rgroh\\eclipse-workspace\\rgrohitg.anki\\src\\test\\resources\\Cards_1.txt";

	@Test
	public void givenInputStream_whenReadFromInputStreamInvoked_ListOfCardsAreReturned() throws IOException {
		CardsReader cardsReader = new CardsReader(new InputStreamReader());
		InputStream inputStream = cardsReader.read(FILE_PATH);
		List<String> cards = cardsReader.readFromInputStream(cardsReader.read(FILE_PATH));
	}
}
