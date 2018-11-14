package com.rgrohitg.anki.file.reader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author rgroh
 *
 */
abstract class AbstractFileReader<T> implements Reader<InputStream> {

	protected Reader<InputStream> reader;

	public AbstractFileReader(Reader<InputStream> reader) {
		this.reader = reader;
	}

	abstract T readFromInputStream(InputStream inputStream) throws IOException;

}