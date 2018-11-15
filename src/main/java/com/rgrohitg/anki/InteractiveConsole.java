package com.rgrohitg.anki;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import com.rgrohitg.anki.file.writer.GameDataStreamWriter;
import com.rgrohitg.anki.file.writer.OutputStreamWriter;
import com.rgrohitg.anki.model.User;
import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.service.GameSession;
import com.rgrohitg.anki.service.GameSessionImpl;
import com.rgrohitg.anki.state.GameState;
import com.rgrohitg.anki.state.RedBox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InteractiveConsole implements AppConsole {

	GameSession session = new GameSessionImpl();
	List<Integer> cardsToStudy = new ArrayList<>();
	List<GameState> eodState = new ArrayList<>();

	@Override
	public void initialize() {
		log.info("Initilizing Game---->");
		session.getSession();
		start();
	}

	@Override
	public void start() {
		System.out.println("-------------------Game starts--------------------->");
		cardsToStudy = session.getCardsToStudy();
		Queue<Integer> q = new ConcurrentLinkedQueue<>();
		q.addAll(cardsToStudy);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Press enter to Start");
		String readString = scanner.nextLine();

		play(cardsToStudy, scanner);
		close();
		save();

	}

	private void play(List<Integer> cards, Scanner scanner) {
		cardsToStudy = session.getCardsToStudy();
		List<Integer> tempList = new ArrayList<>();
		boolean isValid = true;
		while (isValid) {
			cards.stream().forEach(card -> {
				System.out.println("Question : " + card);
				System.out.println("Press enter to Show answer");
				if (scanner.nextLine() != null) {
					System.out.println("ANSWER OF THIS IS ");
				}
				System.out.println("1:RED, 2:ORANGE, 3:GREEN :");
				System.out.println("Enter option to move");
				if (scanner.hasNextLine()) {
					String input = scanner.nextLine();
					System.out.println("Moved to box :" + input);
					if (input.equals("1")) {
						tempList.add(card);
					} else {
						GameState game = session.getGameState().get(card);
						switch (input) {
						case "2":
							game.setColor("ORANGE");
							break;
						case "3":
							game.setColor("GREEN");
							break;
						default:
							game.setColor("RED");
							break;
						}
						game.nextState();
						session.getGameState().put(card, game);
					}
				}

			});
			isValid = false;
			if (!tempList.isEmpty()) {
				play(cardsToStudy, scanner);
			}
		}
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	private void testSaveState() {

		RedBox newState = new RedBox();
		RedBox newState2 = new RedBox();
		RedBox newState3 = new RedBox();

		UserGame userGame = new UserGame();
		userGame.setUser(new User());
		userGame.getUser().setId("user1");
		userGame.getUser().setName("Rohit");
		userGame.setGame(new ArrayList<GameState>());
		List<Integer> cardshashCode = session.getAllCards().entrySet().stream().map(Entry::getKey)
				.collect(Collectors.toList());
		List<GameState> games = new ArrayList<>();

		GameState game1 = new GameState();
		game1.setCard(cardshashCode.get(0));
		game1.setColor("RED");
		newState.next(game1);
		games.add(game1);

		GameState game2 = new GameState();
		game2.setCard(cardshashCode.get(1));
		game2.setColor("ORANGE");
		newState2.next(game2);
		games.add(game2);

		GameState game3 = new GameState();
		game3.setCard(cardshashCode.get(2));
		game3.setColor("RED");
		newState3.next(game3);

		games.add(game3);
		userGame.setGame(games);

		GameDataStreamWriter gameState = new GameDataStreamWriter(new OutputStreamWriter());
		gameState.write(userGame, session.getUserGameStorePath());
	}

	@Override
	public void close() {
		log.info("No more cards to read for today");
		session.getGameState().entrySet().stream().map(Entry::getValue).forEach(eodState::add);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		session.getUserGame().setGame(eodState);
		GameDataStreamWriter gameState = new GameDataStreamWriter(new OutputStreamWriter());
		gameState.write(session.getUserGame(), session.getUserGameStorePath());
	}
}
