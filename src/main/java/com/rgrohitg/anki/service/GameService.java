package com.rgrohitg.anki.service;

import java.util.List;
import java.util.Map;

import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.GameState;

public interface GameService {

	void updateCardsToStudy(List<Integer> cardsToStudy);

	void updateGameState(Map<Integer, GameState> gameState);

	String getUserGameStorePath();

	List<Integer> getCardsToStudy();

	Map<Integer, Card> getAllCards();

	Map<Integer, GameState> getGameState();

	UserGame getUserGame();

	boolean isGameCompleted();

	void preSaveSession();

	void saveSession(UserGame userGame, String userGameStorePath);

	String getQuestion(Integer cardNumber);

	String getAnswer(Integer cardNumber);
}
