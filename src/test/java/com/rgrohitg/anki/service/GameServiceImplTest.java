package com.rgrohitg.anki.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(BlockJUnit4ClassRunner.class)
public class GameServiceImplTest {
	GameManager manager = GameManager.getManager();

	@Test
	public void testStartService() {
		TestUtils.setSystemProperties();
		GameServiceImpl service = new GameServiceImpl();
		List<Integer> cards = service.getCardsToStudy();
		assertNotNull(cards);
		assertEquals(3, cards.size());
		assertNotNull(service.getQuestion(1748572806));
		assertNotNull(service.getAnswer(1748572806));
		assertNotNull(service.getGameState());
		assertNotNull(service.getUserGame());
		assertNotNull(service.getUserGameStorePath());
		assertNotNull(service.getAllCards());

		service.updateGameState(1748572806, service.getGameState().get(1748572806));
		service.preSaveSession();
		service.saveSession(service.getUserGame(), service.getUserGameStorePath());
		assertTrue(service.isGameCompleted());
	}

}
