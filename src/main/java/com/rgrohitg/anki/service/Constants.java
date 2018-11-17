package com.rgrohitg.anki.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

	// CONFIG CONSTANTS
	public static final String CONFIG_USER_GAME_STORE_PATH = "userGameStorePath";
	public static final String CONFIG_FILEPATH = "filepath";
	public static final String CONFIG_FILENAME = "filename";
	public static final String CONFIG_USER_ID = "userId";
	public static final String CONFIG_USER_NAME = "userName";
	public static final String CONFIG_CARDS_FILE_PATH = "cardsFilePath";

	// CONSOLE CONSTANTS
	public static final String CARD_MOVE_MESSAGE = "Card dropped to box :";
	public static final String GAME_STARTS_MESSAGE = "-------------------Game starts--------------------->";
	public static final String START_MESSAGE = "Press enter to Start";
	public static final String QUESTION_MESSAGE = "Question : ";
	public static final String ANSWER_MESSAGE = "Answer : ";
	public static final String SHOW_ANSWER_MESSAGE = "Press enter to Show answer";
	public static final String DROP_BOX_MESSAGE = "Select Box to drop the card";
	public static final String NO_MORE_CARDS_MESSAGE = "No more cards to read for today";
	public static final String CONGRATULATION_MESSAGE = "Congratulation !!! , Game finished succesfully";
	public static final String BOX_OPTION_MESSAGE = "1:RED, 2:ORANGE, 3:GREEN :";

	// INERACTION MODE
	public static final String CONSOLE = "java_console";
	public static final String ANDROID = "android";
	public static final String WEB = "WEB";

	// READ WRITE FACTORY OPTIONS
	public static final String WRITE_MODE = "writeMode";
	public static final String QUESTIONS_READ_MODE = "questionsReadMode";
	public static final String USER_GAME_DATA_READ_MODE = "userGameDataReadMode";

	public static final String JAVA = "java";
	public static final String FILE = "file";

	// BOX colors
	public static final String RED = "RED";
	public static final String ORANGE = "ORANGE";
	public static final String GREEN = "GREEN";

	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String THREE = "3";

}
