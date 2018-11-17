package com.rgrohitg.anki.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.rgrohitg.anki.enums.BoxEnum;
import com.rgrohitg.anki.file.reader.CardsReader;
import com.rgrohitg.anki.file.reader.GameSessionReader;
import com.rgrohitg.anki.file.reader.InputFileReader;
import com.rgrohitg.anki.file.reader.InputStreamReader;
import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.GameState;
import com.rgrohitg.anki.state.RedBox;
import com.rgrohitg.anki.utils.Utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class GameHelper {

	Predicate<Entry<Integer, GameState>> filterRedCards = element -> BoxEnum.RED.name()
			.equals(element.getValue().getColor());

	public List<Integer> getCardsInRedBox(Map<Integer, GameState> gameState) {
		return gameState.entrySet().stream().filter(filterRedCards).map(Entry::getKey).collect(Collectors.toList());
	}

	public Map<Integer, GameState> restore(UserGame userGame) {
		return userGame.getGame().stream().collect(Collectors.toMap(GameState::getCard, Function.identity()));
	}

	public Map<Integer, Card> loadCards(String filePath) {
		if (!Utils.isFileExist(filePath)) {
			log.error("Empty cards path");
		}
		CardsReader cardReader = new CardsReader(new InputFileReader());
		List<String> cards = null;
		try {
			cards = cardReader.read(filePath);
		} catch (IOException e) {
			log.error("Initilization Error ,Unable to readthe cards" + e);
		}
		return cardReader.loadData(cards);
	}

	public UserGame readGameState(String userGameState) {
		UserGame session = null;
		try {
			if (Utils.isFileExist(userGameState)) {
				GameSessionReader reader = new GameSessionReader(new InputStreamReader());
				session = reader.read(userGameState);
			}
		} catch (Exception e) {
			log.error("Error while loading game session ,Corrupted data", e);
			loadError();
		}
		return session;
	}

	public UserGame createNewUserGame(User user, Map<Integer, Card> cards) {
		UserGame session = new UserGame();
		List<GameState> games = new ArrayList<>();
		

		session.setUser(new User());
		session.getUser().setId(user.getId());
		session.getUser().setName(user.getName());
		cards.entrySet().stream().forEach(card -> {
			GameState game = new GameState();
			game.setCard(card.getKey());
			game.setColor(BoxEnum.RED.name());
			game.setBox(new RedBox());
			games.add(game);
		});
		session.setGame(games);
		return session;
	}

	// TODO Need to implement functionality to create new game if file is corrupted
	private void loadError() {
		log.error("Creating new game!!!!!!");
	}

}
