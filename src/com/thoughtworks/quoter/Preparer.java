package com.thoughtworks.quoter;

public class Preparer {
	/**
	 * Static convenience method. Identical to: <br/>
	 * <code>new Preparer(template, arguments).prepared();</code>
	 * 
	 * @param string
	 *          the string to
	 * @return the string with double-quotes and backslashes escaped
	 */
	public static String prepared(final String template, final Object... arguments) {
		return new Preparer(template, arguments).prepared();
	}
	public static Preparer unquoted(final String template, final Object... arguments) {
		return new Preparer(true, template, arguments);
	}

	private final String prepared;

	private boolean unquotedStrings = false;

	private Preparer(boolean unquotedStrings, String template, Object... arguments) {
		this.unquotedStrings = unquotedStrings;
		this.prepared = prepare(template, arguments);
	}

	public Preparer(final String template, final Object... arguments) {
		this(false, template, arguments);
	}

	private String prepare(final String template, final Object... arguments) {
		if (template == null) return template;
		if (arguments == null) return template;

		String result = template;
		for (Object arg : arguments) {
			if (arg == null) continue;

			String replace;
			if (!unquotedStrings && arg instanceof String) replace = Quoter.quoted((String) arg); // only surround strings with double-quotes
			else replace = arg.toString();

			result = result.replaceFirst("(?<!\\\\)\\?", replace); // replace ? but not \?
		}
		return result;
	}

	public String prepared() {
		return prepared;
	}

	@Override
	public String toString() {
		return prepared();
	}
}
