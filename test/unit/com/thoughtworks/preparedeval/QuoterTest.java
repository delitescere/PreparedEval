package com.thoughtworks.preparedeval;

import static org.junit.Assert.*;
import org.junit.Test;

public class QuoterTest {
	@Test
	public void shouldReturnEmptyStringWhenGivenEmptyString() throws Exception {
		assertEquals("", new Quoter("").escaped());
		assertEquals("", Quoter.escaped(""));
	}

	@Test
	public void shouldReturnEscapedStringInQuotes() throws Exception {
		assertEquals("\"He\\\"llo\"", new Quoter("He\"llo").quoted());
		assertEquals("\"He\\\"llo\"", Quoter.quoted("He\"llo"));
	}

	@Test
	public void shouldReturnNullWhenGivenNull() throws Exception {
		assertNull(new Quoter(null).escaped());
		assertNull(Quoter.escaped(null));
	}

	@Test
	public void shouldReturnSameWhenGivenDiacriticalChars() throws Exception {
		final String expected = "¡ªºŭảẖ˝āœˀ";
		assertEquals(expected, new Quoter(expected).escaped());
		assertEquals(expected, Quoter.escaped(expected));
	}

	@Test
	public void shouldReturnSameWhenGivenOnlyAlphaNumChars() throws Exception {
		final String expected = "ABCXYZabcxyz0123";
		assertEquals(expected, new Quoter(expected).escaped());
		assertEquals(expected, Quoter.escaped(expected));
	}

	@Test
	public void shouldReturnSameWhenGivenOnlyNonSpecialChars() throws Exception {
		final String expected = "`~!@#$%^&*()_+-= {}[]|;:<>,.?/";
		assertEquals(expected, new Quoter(expected).escaped());
		assertEquals(expected, Quoter.escaped(expected));
	}

	@Test
	public void shouldReturnSlashDQuoteWhenGivenDQuote() throws Exception {
		assertEquals("\\\"", new Quoter("\"").escaped());
		assertEquals("\\\"", Quoter.escaped("\""));
	}

	@Test
	public void shouldReturnSlashSlashWhenGivenSlash() throws Exception {
		assertEquals("\\\\", new Quoter("\\").escaped());
		assertEquals("\\\\", Quoter.escaped("\\"));
	}

	@Test
	public void shouldReturnSQuoteWhenGivenSQuote() throws Exception {
		assertEquals("'", new Quoter("'").escaped());
		assertEquals("'", Quoter.escaped("'"));
	}

	@Test
	public void shouldReturnStringInQuotes() throws Exception {
		assertEquals("\"Hello\"", new Quoter("Hello").quoted());
		assertEquals("\"Hello\"", Quoter.quoted("Hello"));
	}
}
