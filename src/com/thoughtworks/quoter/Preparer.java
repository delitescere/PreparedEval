package com.thoughtworks.quoter;

public class Preparer {
	private final String prepared;

	public Preparer(final String template, final String... strings) {
		this.prepared = prepare(template, strings);
	}

	private String prepare(final String template, final String... arguments) {
		String result = template;
		for (String arg : arguments) {
			Quoter quoter = new Quoter(arg);
			String replace = quoter.quoted();
			result = result.replaceAll("(?<!\\\\)\\?", replace); // replace ? but not \?
		}
		return result;
	}

	public String prepared() {
		return prepared;
	}

}
