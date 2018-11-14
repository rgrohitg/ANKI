package com.rgrohitg.anki.service;

import java.io.IOException;
import java.util.List;

import com.rgrohitg.anki.file.reader.InputFileReader;
import com.rgrohitg.anki.file.reader.CardsReader;
import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Singleton property class to initialize username & question and answer data
 * 
 * @author rgroh
 *
 */
@Slf4j
public class GameManager {
	private static GameManager manager = null;

	public String USER = null;
	public String FILENAME = null;
	public String FILEPATH = null;
	public String USER_GAME_STATE = null;
	public String ABSOLUTE_CARDS_FILE_PATH = null;
	public List<Card> gameCards = null;

	public static GameManager getManager() {
		if (manager == null)
			manager = new GameManager();

		return manager;
	}

	private GameManager() {
		initializeSystemProperties();
		initializeGameProperties();

	}

	private void initializeSystemProperties() {

		USER = System.getProperty("user");
		FILENAME = System.getProperty("filename");
		FILEPATH = System.getProperty("filepath");
		USER_GAME_STATE = USER + FILENAME;
		ABSOLUTE_CARDS_FILE_PATH = FILEPATH + "\\" + FILENAME;
		if (USER != null && FILENAME != null && FILEPATH != null && USER_GAME_STATE != null
				&& ABSOLUTE_CARDS_FILE_PATH != null) {
			log.debug("Sytem properties are loaded properly!!!");
		} else {
			loadError();
		}

	}

	private void initializeGameProperties() {
		if (Utils.isFileExist(ABSOLUTE_CARDS_FILE_PATH)) {
			CardsReader simpleParser = new CardsReader(new InputFileReader(ABSOLUTE_CARDS_FILE_PATH));

			try {
				gameCards = simpleParser.createReferenceData(simpleParser.read());
			} catch (IOException e) {
				log.error("Unable to read the file " + e);
				e.printStackTrace();
			}
		} else {
			loadError();
		}
	}

	private void loadError() {
		log.error("Unable to load the System properties existing!!!!!!");
		System.exit(0);
	}
}