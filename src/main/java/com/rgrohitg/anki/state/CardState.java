package com.rgrohitg.anki.state;

import java.io.Serializable;

public interface CardState extends Serializable {

	void next(Game game);

	void current(Game game);

	void printStatus();
}