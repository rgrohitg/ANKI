package com.rgrohitg.anki.file.writer;

abstract class AbstractFileWriter<T> implements Writer<T> {

	protected Writer<T> writer;

	public AbstractFileWriter(Writer<T> writer) {
		this.writer = writer;
	}

}
