package com.rgrohitg.anki.console;

import com.rgrohitg.anki.service.Constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsoleFactory {

	public static AppConsole getConsole(String consoleMode) {
		switch (consoleMode) {
		case Constants.JAVACONSOLE:
			return new InteractiveConsole();
		case Constants.ANDROID:
			return new AndroidConsole();
		default:
			return new InteractiveConsole();
		}

	}
}