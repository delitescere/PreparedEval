package com.thoughtworks.quoter;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuoterTest {
	@Test
	public void shouldReturnEmptyStringWhenGivenEmptyString() throws Exception {
		Quoter quoter = new Quoter("");
		assertEquals("", quoter.quoted());
	}

	@Test
	public void shouldReturnNullWhenGivenNull() throws Exception {
		Quoter quoter = new Quoter(null);
		assertNull(quoter.quoted());
	}
}
