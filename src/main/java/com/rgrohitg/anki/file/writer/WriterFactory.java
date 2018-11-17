package com.rgrohitg.anki.file.writer;

import com.rgrohitg.anki.model.UserGame;
import com.rgrohitg.anki.service.Constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WriterFactory {
	/**
	 * This Method can be extended to switch case when there are more write mode
	 * operations
	 * 
	 * @param writeMode
	 * @return
	 */
	public static Writer<UserGame> getWriter(String writeMode) {
		if (writeMode.equals(Constants.JAVA)) {
			return new OutputStreamWriter();
		}
		return null;
	}
}
