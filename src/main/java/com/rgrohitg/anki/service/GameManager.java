package com.rgrohitg.anki.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgrohitg.anki.ConfigProperties;
import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.GameState;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Singleton Manager class to initialize User game , Its a kind of repository
 * class
 * 
 * @author rgroh
 *
 */
@Slf4j
@Data
public class GameManager {

	private static GameManager manager;
	private Map<Integer, Card> cardsHolder;
	private Map<Integer, GameState> gameState;
	private List<Integer> cardsToStudy;
	protected Map<String, String> configMap;

	private GameHelper helper = new GameHelper();
	private UserGame userGame = null;

	private List<Card> gameCards = null;

	protected static GameManager getManager() {
		if (manager == null)
			manager = new GameManager();

		return manager;
	}

	private GameManager() {
		super();
	}

	protected void initializeGame() {
		initializeSystemProperties();
		cardsHolder = loadCards(configMap.get(Constants.CONFIG_CARDS_FILE_PATH));
		userGame = loadGameData();
	}

	protected void initializeSystemProperties() {
		configMap = new HashMap<>();
		configMap.put(Constants.CONFIG_USER_ID, ConfigProperties.USER_ID);
		configMap.put(Constants.CONFIG_FILENAME, ConfigProperties.FILENAME);
		configMap.put(Constants.CONFIG_FILEPATH, ConfigProperties.FILEPATH);
		configMap.put(Constants.CONFIG_USER_GAME_STORE_PATH, ConfigProperties.USER_GAME_STORE_PATH);
		configMap.put(Constants.CONFIG_CARDS_FILE_PATH, ConfigProperties.CARDS_FILE_PATH);
		configMap.put(Constants.WRITE_MODE, ConfigProperties.WRITE_MODE);
		configMap.put(Constants.QUESTIONS_READ_MODE, ConfigProperties.QUESTIONS_READ_MODE);
		configMap.put(Constants.USER_GAME_DATA_READ_MODE, ConfigProperties.USER_GAME_DATA_READ_MODE);
		configMap.put(Constants.CONSOLE_MODE, ConfigProperties.CONSOLE_MODE);

		if (configMap.get(Constants.CONFIG_USER_ID) != null && configMap.get(Constants.CONFIG_FILENAME) != null
				&& configMap.get(Constants.CONFIG_CARDS_FILE_PATH) != null) {
			log.info("Sytem properties are loaded properly!!!");
		} else {
			loadError();
		}

	}

	private Map<Integer, Card> loadCards(String cardsAbsolutePath) {
		return helper.loadCards(cardsAbsolutePath);
	}

	private UserGame loadGameData() {
		UserGame savedUserGame = helper.readGameState(configMap.get(Constants.CONFIG_USER_GAME_STORE_PATH));

		if (savedUserGame == null) {
			User user = new User();
			user.setId(Constants.CONFIG_USER_ID);
			user.setName(Constants.CONFIG_USER_ID);
			savedUserGame = helper.createNewUserGame(user, cardsHolder);
		}

		gameState = helper.restore(savedUserGame);
		cardsToStudy = helper.getCardsInRedBox(gameState);
		return savedUserGame;
	}

	private static void loadError() {
		log.error("Unable to load the System properties existing!!!!!!");
		System.exit(0);
	}

}