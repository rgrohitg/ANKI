package com.rgrohitg.anki.state;

public class GreenBox implements Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void next(GameState game) {
		game.setBox(new OrangeBox());

	}

}
