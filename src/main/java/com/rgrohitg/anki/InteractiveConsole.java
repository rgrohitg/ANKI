package com.rgrohitg.anki;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
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
		scanner.nextLine();

		play(cardsToStudy, scanner);
		close();
		save();
		end();

	}

	private void play(List<Integer> cards, Scanner scanner) {
		cardsToStudy = session.getCardsToStudy();
		List<Integer> tempList = new ArrayList<>();
		boolean isValid = true;
		while (isValid) {
			cards.stream().forEach(card -> {
				String question = session.getAllCards().get(card).getQuestion();
				String answer = session.getAllCards().get(card).getAnswer();
				System.out.println("Question : " + question);
				System.out.println("Press enter to Show answer");
				if (scanner.nextLine() != null) {
					System.out.println("ANSWER :  "+answer );
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

	@Override
	public void close() {
		log.info("No more cards to read for today");
		session.getGameState().entrySet().stream().map(Entry::getValue).forEach(eodState::add);
		session.getGameState().entrySet().stream().forEach(entry->{
			if(entry.getValue().getColor().equals("ORANGE")){
				entry.getValue().setColor("RED");
				entry.getValue().nextState();
			}else if(entry.getValue().getColor().equals("GREEN")){
				entry.getValue().setColor("ORANGE");
				entry.getValue().nextState();
			}			
			});
	}

	Predicate<String> colorPredicate= element->element.equals("RED");
	
	@Override
	public void end() {
		boolean isGameCompleted = session.getUserGame().getGame().stream().map(GameState::getColor).anyMatch(colorPredicate);
		if(!isGameCompleted){
			log.info("User completed the game");
			System.out.println("Congratulation !!! , Game finished succesfully");
		}

	}

	@Override
	public void save() {
		GameDataStreamWriter gameState = new GameDataStreamWriter(new OutputStreamWriter());
		gameState.write(session.getUserGame(), session.getUserGameStorePath());
	}
}
