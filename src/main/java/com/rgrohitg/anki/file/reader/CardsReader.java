package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgrohitg.anki.model.Card;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardsReader extends AbstractFileReader<List<String>> {

	public CardsReader(Reader<List<String>> reader) {
		super(reader);
	}

	@Override
	public List<String> read(String path) throws IOException {
		return reader.read(path);
	}

	public Map<Integer, Card> loadData(List<String> cards) {
		Map<Integer, Card> cardMap = new HashMap<>();
		List<Card> objectCards = new ArrayList<>();

		int numberOfCards = cards.size() - 1;
		log.debug("Number of Cards = " + numberOfCards);

		cards.stream().skip(1).forEach(element -> {
			String[] qa = element.split("\\|");
			Card card = new Card();
			card.setQuestion(qa[0]);
			card.setAnswer(qa[1]);
			objectCards.add(card);
			cardMap.put(qa[0].hashCode(), card);
		});
		return cardMap;
	}

}
