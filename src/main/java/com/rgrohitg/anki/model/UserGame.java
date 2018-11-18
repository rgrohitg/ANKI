package com.rgrohitg.anki.model;

import java.io.Serializable;
import java.util.List;

import com.rgrohitg.anki.state.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private List<GameState> game;
}
