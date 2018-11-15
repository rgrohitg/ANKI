package com.rgrohitg.anki.service;

import java.util.List;
import java.util.Map;

import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.GameState;

public class GameSessionImpl implements GameSession {

	public GameManager manager = null;

	@Override
	public void updateCardsToStudy(List<Integer> cardsToStudy) {
		manager.setCardsToStudy(cardsToStudy);

	}

	@Override
	public void updateGameState(Map<Integer, GameState> gameState) {
		manager.setGameState(gameState);
	}

	@Override
	public GameManager getSession() {
		manager = GameManager.getManager();
		return manager;

	}

	@Override
	public List<Integer> getCardsToStudy() {
		return manager.getCardsToStudy();
	}

	@Override
	public String getUserGameStorePath() {
		return manager.getUserGameStorePath();
	}

	@Override
	public Map<Integer, Card> getAllCards() {
		return manager.getCardsHolder();
	}

	@Override
	public Map<Integer, GameState> getGameState() {
		return manager.getGameState();
	}

	@Override
	public UserGame getUserGame() {
		return manager.getUserGame();
	}

}
