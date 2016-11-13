package models.db;

public class Quote {
	private String quote;
	
	public Quote(String quote) {
		this.quote = quote;
	}
	
	public String getQuoteAsStr() {
		return this.quote;
	}
}
