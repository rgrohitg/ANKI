package com.rgrohitg.anki;

import com.rgrohitg.anki.service.Constants;

/**
 * Config Properties class which will be initialized once
 * 
 * @author rgroh
 *
 */
public class ConfigProperties {

	private ConfigProperties() {
	}

	public static final String USER_ID = System.getProperty(Constants.CONFIG_USER_ID);
	public static final String FILENAME = System.getProperty(Constants.CONFIG_FILENAME);
	public static final String FILEPATH = System.getProperty(Constants.CONFIG_FILEPATH);
	public static final String USER_GAME_STORE_PATH = System.getProperty(Constants.CONFIG_USER_GAME_STORE_PATH);
	public static final String CARDS_FILE_PATH = System.getProperty(Constants.CONFIG_CARDS_FILE_PATH);
	public static final String WRITE_MODE = System.getProperty(Constants.WRITE_MODE);
	public static final String QUESTIONS_READ_MODE = System.getProperty(Constants.QUESTIONS_READ_MODE);
	public static final String USER_GAME_DATA_READ_MODE = System.getProperty(Constants.USER_GAME_DATA_READ_MODE);
	public static final String CONSOLE_MODE = System.getProperty(Constants.CONSOLE_MODE);
}
