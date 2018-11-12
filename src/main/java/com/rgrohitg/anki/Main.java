package com.rgrohitg.anki;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.rgrohitg.anki.file.reader.InputFileReader;
import com.rgrohitg.anki.file.reader.TextFileReader;
import com.rgrohitg.anki.file.writer.GameDataStreamWriter;
import com.rgrohitg.anki.file.writer.OutputStreamWriter;
import com.rgrohitg.anki.model.Card;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.state.Game;
import com.rgrohitg.anki.state.NewState;

public class Main {

	public static void initialize() {
		loadState();
		saveState();
	}

	private static void loadState() {
		TextFileReader simpleParser = new TextFileReader(new InputFileReader(GameProperties.ABSOLUTE_FILE_PATH));
		try {
			FileInputStream fileInputStream = new FileInputStream(GameProperties.USER_GAME_STATE);
			ObjectInputStream is = new ObjectInputStream(fileInputStream);
			List<Card> previousStateLoad = (List<Card>) is.readObject();
			is.close();
			previousStateLoad.stream().forEach(element -> System.out.println(element));
		} catch (Exception e) {
			// Log.e("Error when loading from file.", Log.getStackTraceString(e));
		}
	}

	private static void saveState() {

		TextFileReader simpleParser = new TextFileReader(new InputFileReader(GameProperties.ABSOLUTE_FILE_PATH));

//		try { 
		List<Card> cards = null;
		try {
			cards = simpleParser.createReferenceData(simpleParser.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// FileOutputStream fileStream = new
		// FileOutputStream(GameProperties.USER_GAME_STATE);
//			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
//			objectStream.writeObject(cards); objectStream.close(); fileStream.close(); }
//		catch (FileNotFoundException e) { // TODO Auto-generated catch block
//				e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
//				block e.printStackTrace(); }

		UserGame userGame = new UserGame();
		userGame.setUser(new User());
		userGame.getUser().setId("user1");
		userGame.getUser().setName("Rohit");
		userGame.setGame(new ArrayList<Game>());

		List<Game> games = new ArrayList<Game>();
		Game game1 = new Game();
		game1.setCard(cards.get(1));
		game1.setColor("RED");

		NewState newState = new NewState();
		newState.next(game1);

		Game game2 = new Game();
		game2.setCard(cards.get(2));
		game2.setColor("GREEN");
		newState.next(game2);

		Game game3 = new Game();
		game3.setCard(cards.get(3));
		game3.setColor("ORANGE");
		newState.next(game3);

		Game game4 = new Game();
		game1.setCard(cards.get(1));
		game1.setColor("ORANGE");

		game2.setCard(cards.get(2));
		game2.setColor("ORANGE");
		newState.next(game2);

		game1.setCard(cards.get(1));
		game1.setColor("RED");
		newState.next(game1);

		game3.setCard(cards.get(3));
		game3.setColor("RED");
		newState.next(game3);

		games.add(game1);
		games.add(game2);
		games.add(game3);

		userGame.setGame(games);

		GameDataStreamWriter gameState = new GameDataStreamWriter(new OutputStreamWriter());
		gameState.write(userGame);
	}
}
