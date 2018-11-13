package com.rgrohitg.anki;

import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.file.writer.GameDataStreamWriter;
import com.rgrohitg.anki.file.writer.OutputStreamWriter;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.service.GameManager;
import com.rgrohitg.anki.service.GameSession;
import com.rgrohitg.anki.state.Game;
import com.rgrohitg.anki.state.NewState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	GameManager manager = GameManager.getManager();;

	public void initialize() {
		log.info("Initilizing Game---->");

		GameSession session = GameSession.getSession();
		if (session.getUserGame() == null) {
			log.debug("No existing game data found , Getting things ready for a new Game!!! ");
			session.initializeUserGameData();

		}
		testSaveState();
	}

	private void testSaveState() {

		UserGame userGame = new UserGame();
		userGame.setUser(new User());
		userGame.getUser().setId("user1");
		userGame.getUser().setName("Rohit");
		userGame.setGame(new ArrayList<Game>());

		List<Game> games = new ArrayList<Game>();
		Game game1 = new Game();
		game1.setCard(manager.gameCards.get(0));
		game1.setColor("RED");

		NewState newState = new NewState();
		newState.next(game1);

		Game game2 = new Game();
		game2.setCard(manager.gameCards.get(1));
		game2.setColor("GREEN");
		newState.next(game2);

		Game game3 = new Game();
		game3.setCard(manager.gameCards.get(2));
		game3.setColor("ORANGE");
		newState.next(game3);

		Game game4 = new Game();
		game1.setCard(manager.gameCards.get(0));
		game1.setColor("ORANGE");

		game2.setCard(manager.gameCards.get(1));
		game2.setColor("ORANGE");
		newState.next(game2);

		game1.setCard(manager.gameCards.get(0));
		game1.setColor("RED");
		newState.next(game1);

		game3.setCard(manager.gameCards.get(2));
		game3.setColor("RED");
		newState.next(game3);

		games.add(game1);
		games.add(game2);
		games.add(game3);

		userGame.setGame(games);

		GameDataStreamWriter gameState = new GameDataStreamWriter(new OutputStreamWriter());
		gameState.write(userGame);
	}
}
