package com.rgrohitg.anki.service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.rgrohitg.anki.file.writer.GameDataStreamWriter;
import com.rgrohitg.anki.file.writer.WriterFactory;
import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.BoxColor;
import com.rgrohitg.anki.state.GameState;

public class GameServiceImpl implements GameService {

	protected GameManager manager;

	public GameServiceImpl() {
		manager = GameManager.getManager();
		manager.initializeGame();
	}

	@Override
	public void updateCardsToStudy(List<Integer> cardsToStudy) {
		manager.setCardsToStudy(cardsToStudy);

	}

	@Override
	public void updateGameState(Map<Integer, GameState> gameState) {
		manager.setGameState(gameState);
	}

	@Override
	public List<Integer> getCardsToStudy() {
		return manager.getCardsToStudy();
	}

	@Override
	public String getUserGameStorePath() {
		return manager.getConfigMap().get(Constants.CONFIG_USER_GAME_STORE_PATH);
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

	Predicate<BoxColor> colorPredicate = element -> element.equals(BoxColor.RED);

	@Override
	public boolean isGameCompleted() {
		return getUserGame().getGame().stream().map(GameState::getColor).anyMatch(colorPredicate);
	}

	@Override
	public void preSaveSession() {
		getGameState().entrySet().stream().forEach(entry -> {
			if (entry.getValue().getColor().equals(BoxColor.ORANGE)) {
				entry.getValue().setColor(BoxColor.RED);
				entry.getValue().nextState();
			} else if (entry.getValue().getColor().equals(BoxColor.GREEN)) {
				entry.getValue().setColor(BoxColor.ORANGE);
				entry.getValue().nextState();
			}
		});
	}

	@Override
	public void saveSession(UserGame userGame, String userGameStorePath) {
		GameDataStreamWriter gameState = new GameDataStreamWriter(
				WriterFactory.getWriter(manager.getConfigMap().get(Constants.WRITE_MODE)));
		gameState.write(getUserGame(), getUserGameStorePath());

	}

	@Override
	public String getQuestion(Integer cardNumber) {
		return getAllCards().get(cardNumber).getQuestion();

	}

	@Override
	public String getAnswer(Integer cardNumber) {
		return getAllCards().get(cardNumber).getAnswer();
	}

}
