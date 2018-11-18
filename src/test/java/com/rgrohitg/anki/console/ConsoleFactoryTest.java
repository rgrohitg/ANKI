package com.rgrohitg.anki.console;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.rgrohitg.anki.service.Constants;

@RunWith(BlockJUnit4ClassRunner.class)
public class ConsoleFactoryTest {

	@Test
	public void testGetConsoleFactory() {

		AppConsole java = ConsoleFactory.getConsole(Constants.JAVACONSOLE);
		assertTrue(java instanceof InteractiveConsole);
		AppConsole android = ConsoleFactory.getConsole(Constants.ANDROID);
		assertTrue(android instanceof AndroidConsole);

	}

}
