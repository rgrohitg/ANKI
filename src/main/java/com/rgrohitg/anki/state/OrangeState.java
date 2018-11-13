package com.rgrohitg.anki.state;

public class OrangeState implements CardState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void next(Game game) {
		game.setState(new RedState());
	}

	@Override
	public void current(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printStatus() {
		// TODO Auto-generated method stub

	}

}
