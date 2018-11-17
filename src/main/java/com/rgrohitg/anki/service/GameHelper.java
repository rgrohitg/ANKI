package com.rgrohitg.anki.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.rgrohitg.anki.file.reader.CardsReader;
import com.rgrohitg.anki.file.reader.GameSessionReader;
import com.rgrohitg.anki.file.reader.ReaderFactory;
import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.BoxColor;
import com.rgrohitg.anki.state.GameState;
import com.rgrohitg.anki.state.RedBox;
import com.rgrohitg.anki.utils.Utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class GameHelper {

	public Predicate<Entry<Integer, GameState>> getFilterRedCards() {
		return element -> BoxColor.RED.equals(element.getValue().getColor());
	}

	public List<Integer> getCardsInRedBox(Map<Integer, GameState> gameState) {
		return gameState.entrySet().stream().filter(getFilterRedCards()).map(Entry::getKey)
				.collect(Collectors.toList());
	}

	public Map<Integer, GameState> restore(UserGame userGame) {
		return userGame.getGame().stream().collect(Collectors.toMap(GameState::getCard, Function.identity()));
	}

	public Map<Integer, Card> loadCards(String filePath) {
		if (!Utils.isFileExist(filePath)) {
			log.error("Empty cards path");
		}
		CardsReader cardReader = new CardsReader(
				ReaderFactory.getReader(GameManager.getManager().getConfigMap().get(Constants.QUESTIONS_READ_MODE)));
		List<String> cards = null;
		try {
			cards = cardReader.read(filePath);
		} catch (IOException e) {
			log.error("Initilization Error ,Unable to readthe cards" + e);
		}
		return cardReader.loadData(cards);
	}

	public UserGame readGameState(String userGameState) {
		UserGame userGame = null;
		try {
			if (Utils.isFileExist(userGameState)) {
				GameSessionReader reader = new GameSessionReader(ReaderFactory
						.getReader(GameManager.getManager().getConfigMap().get(Constants.USER_GAME_DATA_READ_MODE)));
				userGame = reader.read(userGameState);
			}
		} catch (Exception e) {
			log.error("Error while loading game session ,Corrupted data", e);
			loadError();
		}
		return userGame;
	}

	public UserGame createNewUserGame(User user, Map<Integer, Card> cards) {
		UserGame userGame = new UserGame();
		List<GameState> games = new ArrayList<>();

		userGame.setUser(new User());
		userGame.getUser().setId(user.getId());
		userGame.getUser().setName(user.getName());
		cards.entrySet().stream().forEach(card -> {
			GameState game = new GameState();
			game.setCard(card.getKey());
			game.setColor(BoxColor.RED);
			game.setBox(new RedBox());
			games.add(game);
		});
		userGame.setGame(games);
		return userGame;
	}

	private void loadError() {
		log.error("Creating new game!!!!!!");
	}

}
