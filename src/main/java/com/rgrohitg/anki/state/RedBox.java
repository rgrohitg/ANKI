package com.rgrohitg.anki.state;

import com.rgrohitg.anki.service.Constants;

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
		switch (game.getColor().name()) {
		case Constants.GREEN:
			state = new GreenBox();
			break;
		case Constants.ORANGE:
			state = new OrangeBox();
			break;
		case Constants.RED:
			state = new RedBox();
			break;
		default:
			log.warn("Oops,This case will never happen");
			break;
		}
		game.setBox(state);

	}

}
