package com.rgrohitg.anki;

import com.rgrohitg.anki.console.AppConsole;
import com.rgrohitg.anki.console.ConsoleFactory;

/**
 * Main class !
 *
 */
public class App {
	public static void main(String[] args) {
		/**
		 * This call can be extended dependency injection / Switch case can be added if
		 * the user interaction mode is different Ex : Console, MobileApp..etc
		 */
		AppConsole main = ConsoleFactory.getConsole(ConfigProperties.CONSOLE_MODE);
		main.run();
	}
}
