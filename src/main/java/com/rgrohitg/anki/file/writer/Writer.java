package com.rgrohitg.anki.file.writer;

@FunctionalInterface
public interface Writer<T> {

	void write(T data, String filePath);
}
