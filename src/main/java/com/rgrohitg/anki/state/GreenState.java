package com.rgrohitg.anki.state;

public class GreenState implements CardState {

	@Override
	public void next(Game game) {
		game.setState(new OrangeState());

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
