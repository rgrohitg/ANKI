package com.rgrohitg.anki.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgrohitg.anki.model.Card;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardsReader extends AbstractFileReader<List<String>> {

	public CardsReader(Reader<InputStream> reader) {
		super(reader);
	}

	@Override
	public InputStream read(String path) throws IOException {
		return reader.read(path);
	}

	// TODO MOVE TO ANOTHER CLASS
	public Map<Integer, Card> loadCardsData(List<String> cards) {
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

	@Override
	public List<String> readFromInputStream(InputStream inputStream) throws IOException {
		List<String> records = new ArrayList<>();

		@Cleanup
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		if (reader.ready()) {
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
		}
		return records;
	}

}
