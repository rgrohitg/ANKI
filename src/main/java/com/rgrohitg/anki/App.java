package com.rgrohitg.anki;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/**
		 * This call can be extended dependency injection / Switch case can be added if
		 * the user interaction mode is different Ex : Console, MobileApp..etc
		 */
		AppConsole main = new InteractiveConsole();
		main.initialize();
	}
}
