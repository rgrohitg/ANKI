package com.rgrohitg.anki.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserTest {

	public final String id = "user1";
	public final String name = "Rohit";

	@Test
	public void testGettersAndSetters() {
		User user = new User();
		user.setId(id);
		assertEquals(user.getId(), id);

		user.setName(name);
		assertEquals(user.getName(), name);
	}
}
