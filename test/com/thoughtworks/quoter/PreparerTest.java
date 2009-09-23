package com.thoughtworks.quoter;

import static org.junit.Assert.*;
import org.junit.Test;

public class PreparerTest {
	@Test
	public void shouldReturnTemplateWhenNoTokens() throws Exception {
		String template = "Hello";
		assertEquals(template, new Preparer(template, (String) null).prepared());
	}

	@Test
	public void shouldReplaceTokenWithString() throws Exception {
		String expected = "Hello \"stranger\"!";
		String template = "Hello ?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void shouldReplaceTwoTokensWithStrings() throws Exception {
		String expected = "Howdy \"partner\", it's \"cold\" here!";
		String template = "Howdy ?, it's ? here!";
		assertEquals(expected, new Preparer(template, "partner", "cold").prepared());
	}

	@Test
	public void shouldNotReplaceEscapedTokenWithString() throws Exception {
		String expected = "Hello \\?!";
		String template = "Hello \\?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void shouldNotReplaceEscapedTokenButReplaceTokenWithString() throws Exception {
		String expected = "Hello \\? \"stranger\"!";
		String template = "Hello \\? ?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}
}
