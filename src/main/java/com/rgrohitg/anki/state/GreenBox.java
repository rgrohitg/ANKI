package com.rgrohitg.anki.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreenBox implements Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void next(GameState game) {
		game.setBox(new OrangeBox());

	}

	@Override
	public void printStatus(GameState game) {
		log.info(game.getColor());

	}

}
