package com.rgrohitg.anki.state;

import java.io.Serializable;

import com.rgrohitg.anki.model.Card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardState state = new NewState();
	private Card card;
	private String color;

	public void currentState() {
		state.current(this);
	}

	public void nextState() {
		state.next(this);
	}

	public void printStatus() {
		state.printStatus();
	}
}