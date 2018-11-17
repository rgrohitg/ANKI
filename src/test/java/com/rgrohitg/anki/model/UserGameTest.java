package com.rgrohitg.anki.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.state.BoxColor;
import com.rgrohitg.anki.state.GameState;
import com.rgrohitg.anki.state.RedBox;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserGameTest {

	private static final String NAME = "Rohit";
	private static final String ID = "user1";

	@Test
	public void testGetterAndSetters() {
		GameState gameState = GameState.builder().card(1).color(BoxColor.RED).box(new RedBox()).build();
		UserGame userGame = new UserGame();
		userGame.setUser(User.builder().id(ID).name(NAME).build());
		userGame.setGame(Arrays.asList(gameState));

		assertEquals(ID, userGame.getUser().getId());
		assertEquals(BoxColor.RED, userGame.getGame().stream().findFirst().get().getColor());
	}
}
