package com.rgrohitg.anki;
/**
 * GameProperties is a kind of property initilizer based on the vm arguments
 * @author rgroh
 *
 */
public class GameProperties {

	public static final String USER=System.getProperty("user");
	public static final String FILENAME=System.getProperty("filename");
	public static final String FILEPATH=System.getProperty("filepath");
	public static final String USER_GAME_STATE=System.getProperty("user").concat(System.getProperty("filename"));
	public static final String ABSOLUTE_FILE_PATH=System.getProperty("filepath") + "\\" +System.getProperty("filename");

}
