package com.rgrohitg.anki.state;

import java.io.Serializable;

public class NewState implements CardState, Serializable {

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
		System.out.println("Initial state, Pending to read cards ");

	}

	@Override
	public void printStatus() {
		System.out.println("");

	}

}
