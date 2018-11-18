package com.rgrohitg.anki.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.rgrohitg.anki.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
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
	}

}
