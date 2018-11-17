package com.rgrohitg.anki.utils;

import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.GameState;

public class TestUtils {

	public static UserGame getDefaultUserGame() {
		List<GameState> games = new ArrayList<>();
		GameState game = GameState.builder().card(1).build();
		games.add(game);
		User user = User.builder().id("user1").name("john").build();
		UserGame userGame = UserGame.builder().user(user).game(games).build();
		return userGame;
	}
}
