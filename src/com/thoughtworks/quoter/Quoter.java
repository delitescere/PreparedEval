package com.thoughtworks.quoter;

public class Quoter {
	/**
	 * Static convenience method. Identical to: <br/>
	 * <code>new Quoter(string).escaped();</code>
	 * 
	 * @param string the string to escape
	 * @return the string with double-quotes and backslashes escaped
	 */
	public static String escaped(final String string) {
		return new Quoter(string).escaped();
	}

	/**
	 * Static convenience method. Identical to: <br/>
	 * <code>new Quoter(string).quoted();</code>
	 * 
	 * @param string the string to quote
	 * @return the escaped string surrounded with double-quotes
	 * @see escaped()
	 */
	public static String quoted(final String string) {
		return new Quoter(string).quoted();
	}

	private final String processed;

	public Quoter(final String string) {
		processed = escape(string);
	}

	private String escape(final String string) {
		if (string == null) return string;

		final StringBuffer buffer = new StringBuffer(string.length() + 16);
		for (int i = 0; i < string.length(); i++) {
			final char ch = string.charAt(i);
			if (ch == '"' || ch == '\\') buffer.append('\\');
			buffer.append(ch);
		}

		return buffer.toString();
	}

	public String escaped() {
		return processed;
	}

	public String quoted() {
		return "\"" + processed + "\"";
	}

	@Override
	public String toString() {
		return escaped();
	}
}
