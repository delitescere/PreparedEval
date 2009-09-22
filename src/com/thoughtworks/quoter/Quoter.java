package com.thoughtworks.quoter;

public class Quoter {
	private String processed;

	public Quoter(String string) {
		processed = quote(string);
	}

	private String quote(String string) {
		return string;
	}

	public String quoted() {
		return processed;
	}

}
