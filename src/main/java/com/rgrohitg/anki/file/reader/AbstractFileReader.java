package com.rgrohitg.anki.file.reader;

/**
 * 
 * @author rgroh
 *
 */
abstract class AbstractFileReader<U> implements Reader<U> {

	protected Reader<U> reader;

	public AbstractFileReader(Reader<U> reader) {
		this.reader = reader;
	}

}