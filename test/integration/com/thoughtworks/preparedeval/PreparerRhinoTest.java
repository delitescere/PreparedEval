package com.thoughtworks.preparedeval;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;

public class PreparerRhinoTest {
	private static Context ctx;
	private static Scriptable scope;

	private static Object eval(final String script) {
		final Object result = ctx.evaluateString(scope, script, "<eval>", 1, null);
		// if (!(result instanceof Undefined)) System.out.println(result.toString());
		return result;
	}

	@BeforeClass
	public static void setupAll() throws Exception {
		ctx = ContextFactory.getGlobal().enterContext();
		scope = ctx.initStandardObjects();
		eval("function id(s) { return s }");
	}

	@AfterClass
	public static void teardownAll() throws Exception {
		Context.exit();
	}

	@Test
	public void shouldFindStringInString() throws Exception {
		final String script = Preparer.prepared("?.indexOf(?)", "Orange", "ran");
		final Object result = eval(script);
		assertEquals(1, result);
	}

	@Test
	public void shouldReturnQuotedWithEscapedQuote() throws Exception {
		final String message = Preparer.prepared("Hello ?!", "wo\"rld");
		final String script = Preparer.prepared("id(?)", message);
		final Object result = eval(script);
		assertEquals("Hello \"wo\\\"rld\"!", result);
	}

	@Test
	public void shouldReturnSimpleQuoted() throws Exception {
		final String message = Preparer.prepared("Hello ?!", "stranger");
		final String script = Preparer.prepared("id(?)", message);
		final Object result = eval(script);
		assertEquals("Hello \"stranger\"!", result);
	}

	@Test
	public void shouldReturnUnquoted() throws Exception {
		final String script = Preparer.prepared("id(?)", "Hello world");
		final Object result = eval(script);
		assertEquals("Hello world", result);
	}
}
