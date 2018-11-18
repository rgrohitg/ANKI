package com.rgrohitg.anki.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameManagerTest {

	@Test
	public void testGameManagerInstance() {

		GameManager manager1 = GameManager.getManager();
		GameManager manager2 = GameManager.getManager();
		assertEquals(manager1, manager2);
	}

	@Test
	public void testInitializeGameWIthoutUserGameState() {
		GameManager manager = GameManager.getManager();
		TestUtils.setSystemProperties();
		manager.initializeGame();
		assertNotNull(manager.getConfigMap());
		assertEquals("Rohit", manager.configMap.get("userId"));

		assertNotNull(manager.getCardsHolder());
	}

	@Test
	public void testInitializeGameWithtUserGameState() {
		GameManager manager = GameManager.getManager();
		TestUtils.setSystemProperties();
		System.setProperty("userGameStorePath", "src/test/resources/correct_user_game_state.txt");
		manager.initializeGame();
		assertNotNull(manager.getConfigMap());
		assertEquals("Rohit", manager.configMap.get("userId"));
		assertNotNull(manager.getCardsHolder());
		assertNotNull(manager.getCardsToStudy());
		assertNull(manager.getGameCards());
		assertNotNull(manager.getGameState());
		assertNotNull(manager.getUserGame());
		manager.setGameState(manager.getGameState());
		manager.setCardsHolder(manager.getCardsHolder());
		manager.setGameCards(manager.getGameCards());
		manager.setUserGame(manager.getUserGame());

	}

	@Test
	public void testInitializeGameWithtEMptyFilePath() {
		GameManager manager = GameManager.getManager();
		TestUtils.setSystemProperties();
		System.setProperty("userGameStorePath", "");
		System.setProperty("cardsFilePath", "");
		manager.configMap.put("userGameStorePath", "");
		manager.configMap.put("cardsFilePath", "");

		manager.initializeGame();
	}

	@Test
	public void testLoadGameWithNewUser() {
		String userId = "user12" + System.currentTimeMillis();

		GameManager manager = GameManager.getManager();
		TestUtils.setSystemProperties();
		System.setProperty("userId", userId);
		System.setProperty("userGameStorePath", "data/" + userId);

		manager.initializeGame();
	}

}