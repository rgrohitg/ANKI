package com.rgrohitg.anki.state;

public class RedState implements CardState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void next(Game game) {
		CardState state = null;
		switch (game.getColor()) {
		case "GREEN":
			state = new GreenState();
			break;
		case "ORANGE":
			state = new OrangeState();
			break;
		case "RED":
			state = new RedState();
			break;
		}
		game.setState(state);

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
