package com.rgrohitg.anki;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rgrohitg.anki.service.Constants;
import com.rgrohitg.anki.service.GameService;
import com.rgrohitg.anki.service.GameServiceImpl;
import com.rgrohitg.anki.state.BoxColor;
import com.rgrohitg.anki.state.GameState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InteractiveConsole implements AppConsole {

	GameService gameService;

	@Override
	public void initialize() {
		log.info("Initilizing Game---->");
		gameService = new GameServiceImpl();
		start();
	}

	@Override
	public void start() {
		consolePrintMessage(Constants.GAME_STARTS_MESSAGE);
		consolePrintMessage(Constants.START_MESSAGE);
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		play(gameService.getCardsToStudy(), scanner);
		save();
		end();

	}

	private void play(List<Integer> cardNumbers, Scanner scanner) {
		List<Integer> tempList = new ArrayList<>();
		boolean isValid = true;
		while (isValid) {
			cardNumbers.stream().forEach(card -> {
				String question = gameService.getQuestion(card);
				String answer = gameService.getAnswer(card);

				consolePrintMessage(Constants.QUESTION_MESSAGE + question);
				consolePrintMessage(Constants.SHOW_ANSWER_MESSAGE);

				scanner.nextLine();
				consolePrintMessage(Constants.ANSWER_MESSAGE + answer);

				consolePrintMessage(Constants.BOX_OPTION_MESSAGE);
				consolePrintMessage(Constants.DROP_BOX_MESSAGE);

				if (scanner.hasNextLine()) {
					String input = scanner.nextLine();
					consolePrintMessage(Constants.CARD_MOVE_MESSAGE + input);
					changeCardState(tempList, card, input);
				}

			});
			isValid = false;
			if (!tempList.isEmpty()) {
				play(tempList, scanner);
			}
		}
	}

	private void changeCardState(List<Integer> tempList, Integer card, String input) {
		if (input.equals(Constants.ONE)) {
			tempList.add(card);
		} else {
			GameState game = gameService.getGameState().get(card);
			switch (input) {
			case Constants.TWO:
				game.setColor(BoxColor.ORANGE);
				break;
			case Constants.THREE:
				game.setColor(BoxColor.GREEN);
				break;
			default:
				game.setColor(BoxColor.RED);
				break;
			}
			game.nextState();
			gameService.getGameState().put(card, game);
		}
	}

	@Override
	public void newGame() {
		// Functinoality if user wants to play same game again
	}

	@Override
	public void end() {
		if (!gameService.isGameCompleted()) {
			log.info("User completed the game");
			consolePrintMessage(Constants.CONGRATULATION_MESSAGE);
		} else {
			log.info(Constants.NO_MORE_CARDS_MESSAGE);
			log.info("Game not completed,Better luck tomorrow");
		}

	}

	@Override
	public void save() {
		gameService.preSaveSession();
		gameService.saveSession(gameService.getUserGame(), gameService.getUserGameStorePath());
	}

	private void consolePrintMessage(String message) {
		System.out.println(message);
	}
}
