package com.thoughtworks.preparedeval;

public class Preparer {
	/**
	 * Static convenience method. Identical to: <br/>
	 * <code>new Preparer(template, arguments).prepared();</code>
	 * 
	 * @param template the string to prepare by substituting question-marks with arguments
	 * @param arguments any string arguments are escaped and quoted
	 * @return the prepared string
	 */
	public static String prepared(final String template, final Object... arguments) {
		return new Preparer(template, arguments).prepared();
	}

	/**
	 * Static convenience method. Identical to: <br/>
	 * <code>new Preparer(true, template, arguments).prepared();</code>
	 * 
	 * @param template the string to prepare by substituting question-marks with arguments
	 * @param arguments any string arguments are escaped but not quoted
	 * @return the prepared string
	 */
	public static String unquoted(final String template, final Object... arguments) {
		return new Preparer(true, template, arguments).prepared();
	}

	private final String prepared;

	private boolean unquotedStrings = false;

	public Preparer(final boolean unquotedStrings, final String template, final Object... arguments) {
		this.unquotedStrings = unquotedStrings;
		prepared = prepare(template, arguments);
	}

	public Preparer(final String template, final Object... arguments) {
		this(false, template, arguments);
	}

	private String prepare(final String template, final Object... arguments) {
		if (template == null || arguments == null) return template;

		String result = template;
		for (final Object arg : arguments) {
			if (arg == null) continue;

			String replace;
			if (arg instanceof String) {
				if (!unquotedStrings) replace = Quoter.quoted((String) arg); // only surround strings with double-quotes
				else replace = Quoter.escaped((String) arg);
				replace = Quoter.escaped(replace); // escape again because replaceFirst (below) is useless with backslashes
			} else replace = arg.toString();

			result = result.replaceFirst("(?<!\\\\)\\?", replace); // replace ? but not \?
		}

		result = result.replaceAll("(?<!\\\\)\\?", ""); // replace any remaining ? with nothing

		result = result.replace("\\?", "?"); // replace escaped ? with ?

		return result;
	}

	/**
	 * @return the prepared string
	 */
	public String prepared() {
		return prepared;
	}

	@Override
	public String toString() {
		return prepared();
	}
}
