package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.model.Card;

public class TextFileReader extends AbstractFileReader {

	public TextFileReader(Reader reader) {
		super(reader);
	}

	@Override
	public List<String> read() throws IOException {
		return reader.read();
	}

	@Override
	public Boolean canRead() {
		// TODO Auto-generated method stub
		return reader.canRead();
	}

	@Override
	public List<Card> createReferenceData(List<String> cards) {
		int numberOfCards = cards.size();
		List<Card> objectCards = new ArrayList<>();
		System.out.println(numberOfCards);
		cards.stream().forEach(element -> {
			System.out.println(element);
			String[] qa = element.split("\\|");
			Card card = new Card();
			card.setQuestion(qa[0]);
			card.setAnswer(qa[1]);
			objectCards.add(card);
		});

		return objectCards;

	}

}
