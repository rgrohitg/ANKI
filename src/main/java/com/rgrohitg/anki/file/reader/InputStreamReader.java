package com.rgrohitg.anki.file.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import com.rgrohitg.anki.model.UserGame;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InputStreamReader<T, U> implements Reader<UserGame> {

	String fileName;

	@Override
	public List<UserGame> read() throws FileNotFoundException, IOException {
		List<UserGame> loadedState = null;
		File file = new File(fileName);
		@Cleanup
		FileInputStream fileInputStream = new FileInputStream(file);
		@Cleanup
		ObjectInputStream is = new ObjectInputStream(fileInputStream);

		try {
			loadedState = (List<UserGame>) is.readObject();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loadedState;
	}

	@Override
	public Boolean canRead() {
		// TODO Auto-generated method stub
		return null;
	}

}
