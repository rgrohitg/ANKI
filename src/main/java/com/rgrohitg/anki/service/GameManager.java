package com.rgrohitg.anki.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Singleton property class to initialize username & question and answer data
 * 
 * @author rgroh
 *
 */
@Slf4j
@Getter
public class GameManager {
	private static GameManager manager = null;
	private Map<Integer, Card> cardsHolder = null;
	private Map<Integer, GameState> gameState = null;

	private UserGame userGame = null;

	private String userId = null;
	private String userName = null;
	private String fileName = null;
	private String filePath = null;
	private String userGameState = null;
	private String cardsAbsolutePath = null;
	private List<Card> gameCards = null;

	public static GameManager getManager() {
		if (manager == null)
			manager = new GameManager();

		return manager;
	}

	private GameManager() {
		initializeSystemProperties();
		cardsHolder = loadCards();
		userGame = loadGameData();
	}

	private Map<Integer, GameState> restore(UserGame userGame) {
		return userGame.getGame().stream().collect(Collectors.toMap(GameState::getCard, Function.identity()));
	}

	private void initializeSystemProperties() {
		userId = System.getProperty("user");
		fileName = System.getProperty("filename");
		filePath = System.getProperty("filepath");
		userGameState = System.getProperty("userSessionPath");
		cardsAbsolutePath = filePath + "\\" + fileName;
		if (userId != null && fileName != null && filePath != null) {
			log.debug("Sytem properties are loaded properly!!!");
		} else {
			loadError();
		}

	}

	private Map<Integer, Card> loadCards() {
		if (!Utils.isFileExist(cardsAbsolutePath)) {
			log.error("Empty cards path");
		}
		CardsReader cardReader = new CardsReader(new InputFileReader());
		List<String> cards = null;
		try {
			cards = cardReader.read(cardsAbsolutePath);
		} catch (IOException e) {
			log.error("Initilization Error ,Unable to readthe cards" + e);
		}
		return cardReader.loadData(cards);

	}

	private UserGame loadGameData() {
		UserGame session = new UserGame();
		List<GameState> games = new ArrayList<>();
		GameState game = new GameState();
		try {
			if (Utils.isFileExist(userGameState)) {
				GameSessionReader reader = new GameSessionReader(new InputStreamReader());
				session = reader.read(userGameState);
				gameState = restore(session);
			} else {
				session.setUser(new User());
				session.getUser().setId(userId);
				session.getUser().setName(userName);
				cardsHolder.entrySet().stream().forEach(card -> {
					game.setCard(card.getKey());
					game.setColor(BoxEnum.RED.name());
					game.setBox(new RedBox());
					games.add(game);
				});
			}
		} catch (Exception e) {
			log.error("Error while loading game session.", e);
		}

		cardsHolder.entrySet().stream().forEach(card -> System.out.println(card.getKey()));
		return session;
	}

	private void loadError() {
		log.error("Unable to load the System properties existing!!!!!!");
		System.exit(0);
	}
}