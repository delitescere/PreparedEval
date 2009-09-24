package com.thoughtworks.quoter;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Test;

public class PreparerTest {
	@Test
	public void shouldEncodeEscapedInnerQuoteForEval() throws Exception {
		final Preparer preparer = new Preparer("Hello ?", "wo\"rld"); // very close to what you end up with
		final String expected = "Hello \\\"wo\\\\\\\"rld\\\""; // the horror you would have to deal with!
		assertEquals(expected, preparer.preparedForEval());
	}

	@Test
	public void shouldEscapeForEval() throws Exception {
		final Preparer preparer = new Preparer("Hello ?", "world");
		assertEquals("Hello \\\"world\\\"", preparer.preparedForEval());
	}

	@Test
	public void shouldNotQuoteString() throws Exception {
		assertEquals("Hello world", Preparer.unquoted("Hello ?", "world"));
	}

	@Test
	public void shouldNotReplaceEscapedToken() throws Exception {
		final String expected = "Hello \\?!";
		final String template = "Hello \\?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void shouldNotReplaceEscapedTokenButReplaceTokenWithQuotedString() throws Exception {
		final String expected = "Hello \\? \"stranger\"!";
		final String template = "Hello \\? ?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void shouldReplaceFirstTokenWithIntegerAndLeaveSecondTokenWithNullArg() throws Exception {
		final String expected = "1 ?";
		final String template = "? ?";
		assertEquals(expected, new Preparer(template, 1, null).prepared());
	}

	@Test
	public void shouldReplaceTokenWithBigDecimal() throws Exception {
		assertEquals("pi is 3.14159", new Preparer("pi is ?", new BigDecimal("2.14159").add(BigDecimal.ONE)).prepared());
	}

	@Test
	public void shouldReplaceTokenWithFloat() throws Exception {
		assertEquals("9.8 m/s/s", new Preparer("? m/s/s", 9.8).prepared());
	}

	@Test
	public void shouldReplaceTokenWithLong() throws Exception {
		assertEquals("\"Take\" 5", new Preparer("? ?", "Take", 5L).prepared());
	}

	@Test
	public void shouldReplaceTokenWithQuotedAndEscapedString() throws Exception {
		final String prepared = Preparer.prepared("Hello ?", "wo\"rld");
		final String expected = "Hello \"wo\\\"rld\"";
		assertEquals(expected, prepared);
	}

	@Test
	public void shouldReplaceTokenWithQuotedString() throws Exception {
		final String expected = "Hello \"stranger\"!";
		final String template = "Hello ?!";
		assertEquals(expected, new Preparer(template, "stranger").prepared());
	}

	@Test
	public void shouldReplaceTokenWithUnquotedButEscapedString() throws Exception {
		assertEquals("Hello wo\\\"rld", Preparer.unquoted("Hello ?", "wo\"rld"));
	}

	@Test
	public void shouldReplaceTokenWithUnquotedInteger() throws Exception {
		assertEquals("Hi-5", Preparer.prepared("Hi-?", 5));
	}

	@Test
	public void shouldReplaceTwoTokensWithQuotedStrings() throws Exception {
		final String expected = "Howdy \"partner\", it's \"cold\" here!";
		final String template = "Howdy ?, it's ? here!";
		assertEquals(expected, new Preparer(template, "partner", "cold").prepared());
	}

	@Test
	public void shouldReturnNullWhenTemplateNull() throws Exception {
		assertEquals(null, new Preparer(null).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenNoArgs() throws Exception {
		assertEquals("", new Preparer("").prepared());
	}

	@Test
	public void shouldReturnTemplateWhenNoTokens() throws Exception {
		final String template = "Hello";
		assertEquals(template, new Preparer(template, (Object) null).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenTokensButEmptyArgs() throws Exception {
		final String template = "Hello ?";
		assertEquals(template, new Preparer(template, new Object[] {}).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenTokensButNoArgs() throws Exception {
		final String template = "Hello ?";
		assertEquals(template, new Preparer(template).prepared());
	}

	@Test
	public void shouldReturnTemplateWhenTokensButNullArg() throws Exception {
		final String template = "Hello ?";
		assertEquals(template, new Preparer(template, (Object) null).prepared());
	}

	@Test
	public void testToString() throws Exception {
		final Preparer preparer = new Preparer("Hello ?", "world");
		assertEquals("Hello \"world\"", "" + preparer);
	}

	@Test
	public void testToStringReturnsSameAsPrepared() throws Exception {
		final Preparer preparer = new Preparer("Hello ?", "world");
		assertEquals(preparer.prepared(), preparer.toString());
	}
}
