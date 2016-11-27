package models.db;

public class TextItem {
	private Quote quote;
	private Category category;
	private Source source;
	
	public TextItem(Quote quote, Category category, Source source) {
		this.quote = quote;
		this.category = category;
		this.source = source;
	}
	
	public Quote getQuote() {
		return this.quote;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public Source getSource() {
		return this.source;
	}
	
	@Override
	public String toString() {
		return this.quote.getQuoteAsStr() + CS + this.category.toString() + CS + source.toString();
	}
	
	private final static String CS = ", ";
}