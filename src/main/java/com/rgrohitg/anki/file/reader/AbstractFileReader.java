package com.rgrohitg.anki.file.reader;

import java.util.List;

import com.rgrohitg.anki.model.Card;

/**
 * 
 * @author rgroh
 *
 */
abstract public class AbstractFileReader implements Reader {

	protected Reader reader;

	public AbstractFileReader(Reader reader) {
		this.reader = reader;
	}

	abstract public List<Card> createReferenceData(List<String> data);
}