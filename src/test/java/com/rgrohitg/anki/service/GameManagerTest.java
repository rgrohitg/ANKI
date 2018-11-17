package com.rgrohitg.anki.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class GameManagerTest {

	@Test
	public void testGameManagerInstance() {

		GameManager manager1 = GameManager.getManager();
		GameManager manager2 = GameManager.getManager();
		assertEquals(manager1, manager2);
	}

	@Test
	public void testInitializeGame() {
		GameManager manager = GameManager.getManager();

	}
}
