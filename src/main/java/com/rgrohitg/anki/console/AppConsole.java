package com.rgrohitg.anki.console;

/**
 * App console is to provide the contract for different client implementation.
 * 
 * @author rgroh
 *
 */
public interface AppConsole {
	void run();

	void initialize();

	void start();

	void newGame();

	void end();

	void save();

}
