package com.rgrohitg.anki.state;

import java.io.Serializable;

interface Box extends Serializable {

	void next(GameState game);

}