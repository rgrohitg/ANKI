package com.rgrohitg.anki.file.writer;

import javax.xml.bind.util.JAXBResult;

/**
 * Dummy Implementation To write data in different format
 * this class will call JsonWriter to store the data
 * @author rtgh
 *
 */
public class GameJsonStreamWriter extends AbstractFileWriter<JAXBResult> {

	public GameJsonStreamWriter(Writer<JAXBResult> writer) {
		super(writer);
		
	}

	@Override
	public void write(JAXBResult data, String filePath) {
		writer.write(data, filePath);
		
	}

}
