package com.rgrohitg.anki.state;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class GameStateTest {

	@Test
	public void givenCardIsInRedStatus_whenAnswerIsComplete_thenAfterTwoDaysCardShouldbeInRedBox() {

		GameState gameState = GameState.builder().card(1).color(BoxColor.RED).box(new RedBox()).build();
		gameState.setColor(BoxColor.GREEN);
		gameState.nextState();
		assertTrue(gameState.getBox() instanceof GreenBox);

		gameState.nextState();
		assertTrue(gameState.getBox() instanceof OrangeBox);

		gameState.nextState();
		assertTrue(gameState.getBox() instanceof RedBox);

	}

	@Test
	public void givenCardIsInRedStatus_whenAnswerIsPartial_thenStateShouldChangeToOrangeAndSholdBereadyToreadnextDay() {

		GameState gameState = GameState.builder().card(1).color(BoxColor.RED).box(new RedBox()).build();
		gameState.setColor(BoxColor.ORANGE);
		gameState.nextState();
		assertTrue(gameState.getBox() instanceof OrangeBox);

		gameState.nextState();
		assertTrue(gameState.getBox() instanceof RedBox);

	}

	@Test
	public void givenCardIsInRedStatus_whenAnswerIsNotKnown_thenStateShouldRemainInRedAndForReadingSameDay() {

		GameState gameState = GameState.builder().card(1).color(BoxColor.RED).box(new RedBox()).build();
		gameState.setColor(BoxColor.RED);
		gameState.nextState();
		assertTrue(gameState.getBox() instanceof RedBox);

		gameState.nextState();
		assertTrue(gameState.getBox() instanceof RedBox);

	}
}
