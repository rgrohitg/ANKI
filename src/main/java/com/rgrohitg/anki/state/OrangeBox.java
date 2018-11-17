package com.rgrohitg.anki.state;

public class OrangeBox implements Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void next(GameState game) {
		game.setBox(new RedBox());
	}

}
