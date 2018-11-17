package com.rgrohitg.anki.service;

import java.util.List;
import java.util.Map;

import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.GameState;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Singleton property class to initialize username & question and answer data
 * 
 * @author rgroh
 *
 */
@Slf4j
@Data
public class GameManager {
	private static GameManager manager = null;
	private Map<Integer, Card> cardsHolder = null;
	private Map<Integer, GameState> gameState = null;
	private List<Integer> cardsToStudy = null;

	private GameHelper helper = new GameHelper();
	private UserGame userGame = null;

	private String userId = null;
	private String userName = null;
	private String fileName = null;
	private String resourcesLocation = null;
	private String userGameStorePath = null;
	private String cardsFilePath = null;
	private List<Card> gameCards = null;

	public static GameManager getManager() {
		if (manager == null)
			manager = new GameManager();

		return manager;
	}

	private GameManager() {
		initializeSystemProperties();
		cardsHolder = loadCards(cardsFilePath);
		userGame = loadGameData();
	}

	private void initializeSystemProperties() {
		userId = System.getProperty("user");
		fileName = System.getProperty("filename");
		resourcesLocation = System.getProperty("filepath");
		userGameStorePath = System.getProperty("userSessionPath");
		cardsFilePath = resourcesLocation + "\\" + fileName;
		if (userId != null && fileName != null && resourcesLocation != null) {
			log.debug("Sytem properties are loaded properly!!!");
		} else {
			loadError();
		}

	}

	private Map<Integer, Card> loadCards(String cardsAbsolutePath) {
		return helper.loadCards(cardsAbsolutePath);
	}

	private UserGame loadGameData() {
		UserGame session = helper.readGameState(userGameStorePath);

		if (session == null) {
			User user = new User();
			user.setId(userId);
			user.setName(userName);
			session = helper.createNewUserGame(user, cardsHolder);
		}

		gameState = helper.restore(session);
		cardsToStudy = helper.getCardsInRedBox(gameState);
		return session;
	}

	private void loadError() {
		log.error("Unable to load the System properties existing!!!!!!");
		System.exit(0);
	}

}