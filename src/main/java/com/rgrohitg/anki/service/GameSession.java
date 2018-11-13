package com.rgrohitg.anki.service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameSession {
	GameManager manager = GameManager.getManager();
	private static GameSession session = null;
	private UserGame userGame = null;

	public static GameSession getSession() {
		if (session == null)
			session = new GameSession();
		return session;
	}

	private GameSession() {
		loadGameData();
	}

	public void initializeUserGameData() {
		setUserGame(new UserGame());
	}

	private void loadGameData() {
		try {
			if (Utils.isFileExist(manager.USER_GAME_STATE)) {
				FileInputStream fileInputStream = new FileInputStream(manager.USER_GAME_STATE);
				ObjectInputStream is = new ObjectInputStream(fileInputStream);
				setUserGame((UserGame) is.readObject());
				is.close();
			}
		} catch (Exception e) {
			log.error("Error while loading game session.", e);
			e.printStackTrace();
		}
	}

	public UserGame getUserGame() {
		return userGame;
	}

	private void setUserGame(UserGame userGame) {
		this.userGame = userGame;
	}
}
