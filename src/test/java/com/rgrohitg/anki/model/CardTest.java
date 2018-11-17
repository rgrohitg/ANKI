package com.rgrohitg.anki.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CardTest {

	public final String question = "What enzyme breaks down sugars mouth and digestive tract?";
	public final String answer = "Amylase";

	@Test
	public void testGettersAndSetters() {
		Card card = new Card();
		card.setQuestion(question);
		assertEquals(card.getQuestion(), question);

		card.setAnswer(answer);
		assertEquals(card.getAnswer(), answer);
	}

	@Test
	public void testGettersAndSettersWithBuilder() {
		Card card = Card.builder().question(question).answer(answer).build();
		assertEquals(card.getQuestion(), question);
		assertEquals(card.getAnswer(), answer);
	}
}
