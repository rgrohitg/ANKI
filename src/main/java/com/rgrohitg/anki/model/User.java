package com.rgrohitg.anki.model;

import java.io.Serializable;

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
public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
}
