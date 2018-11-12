package com.rgrohitg.anki.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String question;
	private String answer;
}
