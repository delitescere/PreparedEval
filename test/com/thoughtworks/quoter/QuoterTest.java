package com.thoughtworks.quoter;

import static org.junit.Assert.*;
import org.junit.Test;

public class QuoterTest {
	@Test
	public void shouldReturnNullWhenGivenNull() throws Exception {
		assertNull(new Quoter(null).quoted());
		assertNull(Quoter.quoted(null));
	}

	@Test
	public void shouldReturnEmptyStringWhenGivenEmptyString() throws Exception {
		assertEquals("", new Quoter("").quoted());
		assertEquals("", Quoter.quoted(""));
	}

	@Test
	public void shouldReturnSameWhenGivenOnlyAlphaNumChars() throws Exception {
		String expected = "ABCXYZabcxyz0123";
		assertEquals(expected, new Quoter(expected).quoted());
		assertEquals(expected, Quoter.quoted(expected));
	}

	@Test
	public void shouldReturnSameWhenGivenOnlyNonSpecialChars() throws Exception {
		String expected = "`~!@#$%^&*()_+-= {}[]|;:<>,.?/";
		assertEquals(expected, new Quoter(expected).quoted());
		assertEquals(expected, Quoter.quoted(expected));
	}

	@Test
	public void shouldReturnSameWhenGivenDiacriticalChars() throws Exception {
		String expected = "¡ªºŭảẖ˝āœˀ";
		assertEquals(expected, new Quoter(expected).quoted());
		assertEquals(expected, Quoter.quoted(expected));
	}

	@Test
	public void shouldReturnSlashDQuoteWhenGivenDQuote() throws Exception {
		assertEquals("\\\"", new Quoter("\"").quoted());
		assertEquals("\\\"", Quoter.quoted("\""));
	}

	@Test
	public void shouldReturnSQuoteWhenGivenSQuote() throws Exception {
		assertEquals("'", new Quoter("'").quoted());
		assertEquals("'", Quoter.quoted("'"));
	}

	@Test
	public void shouldReturnSlashSlashWhenGivenSlash() throws Exception {
		assertEquals("\\\\", new Quoter("\\").quoted());
		assertEquals("\\\\", Quoter.quoted("\\"));
	}
}
