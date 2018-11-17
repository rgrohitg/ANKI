package com.rgrohitg.anki.state;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Box box;
	private Integer card;
	private BoxColor color;

	public void nextState() {
		box.next(this);
	}

}