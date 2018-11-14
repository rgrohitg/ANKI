package com.rgrohitg.anki.model;

import java.io.Serializable;
import java.util.List;

import com.rgrohitg.anki.state.Game;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private List<Game> game;
}
