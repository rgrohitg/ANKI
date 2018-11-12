package com.rgrohitg.anki.file.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Reader<U> {

	List<U> read() throws FileNotFoundException, IOException;

	Boolean canRead();
}
