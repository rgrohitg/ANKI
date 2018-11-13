package com.rgrohitg.anki.file.writer;

@FunctionalInterface
public interface Writer<T> {

	T write(T data);
}
