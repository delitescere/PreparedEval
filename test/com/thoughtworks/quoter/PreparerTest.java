package com.thoughtworks.quoter;

import static org.junit.Assert.*;
import org.junit.Test;

public class PreparerTest {
	@Test
	public void shouldReturnNullWhenTemplateNull() throws Exception {
		assertEquals(null, new Preparer(null).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenNoArgs() throws Exception {
		assertEquals("", new Preparer("").prepared());
	}

	@Test
	public void shouldReturnTemplateWhenTokensButNoArgs() throws Exception {
		String template = "Hello ?";
		assertEquals(template, new Preparer(template).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenTokensButNullArg() throws Exception {
		String template = "Hello ?";
		assertEquals(template, new Preparer(template, (Object) null).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenTokensButEmptyArgs() throws Exception {
		String template = "Hello ?";
		assertEquals(template, new Preparer(template, new Object[] {}).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenNoTokens() throws Exception {
		String template = "Hello";
		assertEquals(template, new Preparer(template, (Object) null).prepared());
	}

	@Test
	public void shouldReplaceTokenWithQuotedString() throws Exception {
		String expected = "Hello \"stranger\"!";
		String template = "Hello ?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void shouldReplaceFirstTokenWithIntegerAndLeaveSecondTokenWithNullArg() throws Exception {
		String expected = "1 ?";
		String template = "? ?";
		assertEquals(expected, new Preparer(template, 1, null).prepared());
	}

	@Test
	public void shouldReplaceTwoTokensWithQuotedStrings() throws Exception {
		String expected = "Howdy \"partner\", it's \"cold\" here!";
		String template = "Howdy ?, it's ? here!";
		assertEquals(expected, new Preparer(template, "partner", "cold").prepared());
	}

	@Test
	public void shouldReplaceTokenWithUnquotedInteger() throws Exception {
		assertEquals("Hi-5", Preparer.prepared("Hi-?", 5));
	}

	@Test
	public void shouldNotQuoteString() throws Exception {
		assertEquals("Hello world", Preparer.unquoted("Hello ?", "world").prepared());
	}

	@Test
	public void shouldNotReplaceEscapedToken() throws Exception {
		String expected = "Hello \\?!";
		String template = "Hello \\?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void testToStringReturnsSameAsPrepared() throws Exception {
		Preparer preparer = new Preparer("Hello ?", "world");
		assertEquals(preparer.prepared(), preparer.toString());
	}

	@Test
	public void testToString() throws Exception {
		Preparer preparer = new Preparer("Hello ?", "world");
		assertEquals("Hello \"world\"", "" + preparer);
	}

	@Test
	public void shouldNotReplaceEscapedTokenButReplaceTokenWithQuotedString() throws Exception {
		String expected = "Hello \\? \"stranger\"!";
		String template = "Hello \\? ?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}
}
