package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.model.Card;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextFileReader extends AbstractFileReader {

	public TextFileReader(Reader reader) {
		super(reader);
	}

	@Override
	public List<String> read() throws IOException {
		return reader.read();
	}

	@Override
	public List<Card> createReferenceData(List<String> cards) {
		int numberOfCards = cards.size() - 1;
		List<Card> objectCards = new ArrayList<>();
		log.debug("Number of Cards = " + numberOfCards);
		cards.stream().skip(1).forEach(element -> {
			String[] qa = element.split("\\|");
			Card card = new Card();
			card.setQuestion(qa[0]);
			card.setAnswer(qa[1]);
			objectCards.add(card);
			System.out.println(element);
		});

		return objectCards;

	}

}
