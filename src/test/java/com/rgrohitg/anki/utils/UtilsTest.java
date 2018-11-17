package com.rgrohitg.anki.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class UtilsTest {

	@Test
	public void testIsFileExistsTrue() {
		boolean result = Utils.isFileExist("src/test/resources/Cards_1.txt");
		assertTrue(result);

	}

	@Test
	public void testIsFileExistsFalse() {
		boolean result = Utils.isFileExist("src/test/resources/Cards_XYZ.txt");
		assertFalse(result);

	}

	@Test
	public void testIsDirectoryExistsFalse() {
		boolean result = Utils.isFileExist("src/test/resources/xes/");
		assertFalse(result);

	}

	@Test
	public void testIsDirectoryExistsTrue() {
		boolean result = Utils.isFileExist("src/test/resources/");
		assertTrue(result);

	}

}
