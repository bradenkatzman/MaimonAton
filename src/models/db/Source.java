package models.db;

import java.util.ArrayList;

public class Source {

	private String source_name;
	private String page_number;
	private ArrayList<String> other_sources;
	
	
	/**
	 * Single param constructor, just source name
	 * 
	 * @param source_name
	 */
	public Source(String source_name) {
		this.source_name = source_name;
		this.other_sources = new ArrayList<String>();
	}
	
	public Source(String source_name, String page_number) {
		this.source_name = source_name;
		this.page_number = page_number;
		this.other_sources = new ArrayList<String>();
	}
	
	/**
	 * Source name and other sources
	 * 
	 * @param source_name
	 * @param other_sources
	 */
	public Source(String source_name, ArrayList<String> other_sources) {
		
	}
	
	/**
	 * Source name, page number constructor
	 * 
	 * @param source_name
	 * @param page_number
	 * @param other_sources
	 */
	public Source(String source_name, String page_number, ArrayList<String> other_sources) {
		this.source_name = source_name;
		this.page_number = page_number;
		this.other_sources = other_sources;
	}
	
	public void addOtherSource(String src) {
		if (src != null) {
			other_sources.add(src);
		}
	}
	
	public String getSourceName() {
		return this.source_name;
	}
	
	public String getPageNumber() {
		if (this.page_number != null) {
			return this.page_number;
		}
		return "N/A";
	}
	
	public ArrayList<String> getOtherSources() {
		return this.other_sources;
	}
	
	@Override
	public String toString() {
		String str = this.source_name;
		
		if (this.page_number != null) {
			str += (CS + this.page_number);
		}
		
		if (!this.other_sources.isEmpty()) {
			str += (CS + "Other sources: ");
			for (String s : other_sources) {
				str += (s + CS);
			}
		}
		return str;
	}
	
	private final static String CS = ", ";
}