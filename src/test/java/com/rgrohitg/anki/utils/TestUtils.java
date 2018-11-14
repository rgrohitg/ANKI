package com.rgrohitg.anki.utils;

import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.Game;

public class TestUtils {

	public static UserGame getDefaultUserGame() {
		List<Game> games = new ArrayList<>();
		Card card = Card.builder().question("What enzyme breaks down sugars mouth and digestive tract?")
				.answer("Amylase").build();
		Game game = Game.builder().card(card).build();
		games.add(game);
		User user = User.builder().id("user1").name("john").build();
		UserGame userGame = UserGame.builder().user(user).game(games).build();
		return userGame;
	}
}
