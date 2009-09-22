package com.thoughtworks.quoter;

public class Quoter {
	private String processed;

	public Quoter(String string) {
		processed = quote(string);
	}

	private String quote(String string) {
		if (string == null) return string;

		StringBuffer buffer = new StringBuffer(string.length() + 16);
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (ch == '"' || ch == '\\') buffer.append('\\');
			buffer.append(ch);
		}

		return buffer.toString();
	}

	public String quoted() {
		return processed;
	}

	/**
	 * Static convenience method. Identical to: <br/>
	 * <code>new Quoter(string).quoted();</code>
	 * 
	 * @param string
	 *          the string to quote
	 * @return the string with double-quotes and backslashes escaped
	 */
	public static String quoted(String string) {
		return new Quoter(string).quoted();
	}
}
