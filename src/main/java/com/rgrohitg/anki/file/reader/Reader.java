package com.rgrohitg.anki.file.reader;

import java.io.IOException;

public interface Reader<T> {

	T read(String path) throws IOException;

}
