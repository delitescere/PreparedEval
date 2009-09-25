package com.thoughtworks.preparedeval;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

public class PreparerRhinoTest {
	private static Context ctx;
	private static Scriptable scope;

	private static Object eval(final String script) {
		final Object result = ctx.evaluateString(scope, script, "<eval>", 1, null);
		if (!(result instanceof Undefined)) System.out.println(result.toString());
		return result;
	}

	@BeforeClass
	public static void setupAll() throws Exception {
		ctx = ContextFactory.getGlobal().enterContext();
		scope = ctx.initStandardObjects();
		eval("function alert(s) { return s; }"); // mimic browser alert() function
	}

	@AfterClass
	public static void teardownAll() throws Exception {
		Context.exit();
	}

	@Test
	public void should() throws Exception {
		final String script = Preparer.prepared("substring(?, ?)", "Orange", "ran");
		System.out.println(script);
		final Object result = eval(script);
		// System.out.println(result);
	}

	@Test
	public void shouldShowAlert() throws Exception {
		final String message = Preparer.prepared("Hello ?, how are you?", "stranger");
		System.out.println(message);
		final String script = Preparer.prepared("alert(?)", message);
		System.out.println(script);
		final Object result = eval(script);
		// System.out.println(result);
	}

	@Test
	public void shouldShowAlert2() throws Exception {
		final String script = Preparer.prepared("alert(?)", "Hello world");
		System.out.println(script);
		final Object result = eval(script);
		// System.out.println(result);
	}
}
