package com.rgrohitg.anki;

import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.file.writer.GameDataStreamWriter;
import com.rgrohitg.anki.file.writer.OutputStreamWriter;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.service.GameManager;
import com.rgrohitg.anki.state.GameState;
import com.rgrohitg.anki.state.RedBox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	GameManager manager = null;

	public void initialize() {
		log.info("Initilizing Game---->");
		manager = GameManager.getManager();
		start();
		testSaveState();
	}

	private void start() {
		// TODO Auto-generated method stub

	}

	private void testSaveState() {

		UserGame userGame = new UserGame();
		userGame.setUser(new User());
		userGame.getUser().setId("user1");
		userGame.getUser().setName("Rohit");
		userGame.setGame(new ArrayList<GameState>());

		List<GameState> games = new ArrayList<>();
		GameState game1 = new GameState();
		game1.setCard(manager.getCardsHolder().entrySet().stream().findFirst().get().getKey());
		game1.setColor("RED");

		RedBox newState = new RedBox();
		newState.next(game1);

		games.add(game1);
		userGame.setGame(games);

		GameDataStreamWriter gameState = new GameDataStreamWriter(new OutputStreamWriter());
		gameState.write(userGame, manager.getUserGameStorePath());
	}
}
