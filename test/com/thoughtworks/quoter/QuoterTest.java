package com.thoughtworks.quoter;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuoterTest {
	@Test
	public void should() throws Exception {
		Quoter quoter = new Quoter("");
		assertEquals("", quoter.quoted());
	}
}
