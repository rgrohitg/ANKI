package com.rgrohitg.anki.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedBox implements Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void next(GameState game) {
		Box state = null;
		switch (game.getColor()) {
		case "GREEN":
			state = new GreenBox();
			break;
		case "ORANGE":
			state = new OrangeBox();
			break;
		case "RED":
			state = new RedBox();
			break;
		default:
			// TODO
			break;
		}
		game.setBox(state);

	}

	@Override
	public void printStatus(GameState game) {
		log.info(game.getColor());
	}

}
