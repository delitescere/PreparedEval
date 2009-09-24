package com.thoughtworks.quoter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

public class PreparerRhinoTest {
	private static Context ctx;
	private static Scriptable scope;

	private static Object eval(final String script) {
		final Object result = ctx.evaluateString(scope, script, "eval", 1, null);
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
}
