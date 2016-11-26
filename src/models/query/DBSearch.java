package models.query;

import models.db.RelationalDB;
import models.db.RelationalDBUtil;
import models.db.Source;
import models.db.TextItem;
import models.db.Category;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.PatternSyntaxException;
import models.db.Quote;

public class DBSearch {
	
	private RelationalDB db;
	Random r;
	
	public DBSearch(RelationalDB db) {
		this.db = db;
		r = new Random();
	}
	
	/**
	 * Queries the db and returns the TextItem which has the highest number of matches with the query string
	 * 
	 * @param query
	 * @return the TextItem with the highest match score
	 */
	public TextItem maxMatchSearch(String query) {
		String[] queryArr = null;
		try {
			queryArr = query.toLowerCase().split("\\s+");
		} catch (PatternSyntaxException ex) {
		    ex.printStackTrace();
		}
		
		int catIdx = -1;
		// first check if there is a category keyword in the question
		for (Category c : Category.values()) {
			if (catIdx != -1) break;
			
			// check if multiple words, tokenize and search independently if so
			if (c.getCatName().contains(SPACE)) {
				for (int i = 0; i < queryArr.length; i++) {
					if (catIdx != -1) break;
					
					if (c.getCatName().contains(SPACE)) {
						StringTokenizer st = new StringTokenizer(c.getCatName(), SPACE);
						
						while (st.hasMoreTokens()) {
							String token = st.nextToken().toLowerCase();
							if (token.equals(OF)) continue;
							
							
							if (queryArr[i].equals(token)) {
								catIdx = (int) RelationalDBUtil.catDescr2idx2Cat.get(c.getCatName()).get(RelationalDBUtil.DBVALUE_IDX);
								break;
							}
						}
					}
					else { 
						if (queryArr[i].equals(c.getCatName().toLowerCase())) {
							catIdx = (int) RelationalDBUtil.catDescr2idx2Cat.get(c.getCatName()).get(RelationalDBUtil.DBVALUE_IDX);
							break;
						}	
					}
				}
			}
		}	
		
		// check if there was a category match
		if (catIdx != -1) { // perform a keyword maximization search on only the entries in this category
			int maxMatchScore = -1;
			int maxMatchIdx = -1;
			for (int i = 0; i < db.getDB().get(catIdx).size(); i++) {
				int currMatchScore = 0;
				
				// split up the quote into tokens
				String quote = db.getDB().get(catIdx).get(i).getQuote().getQuoteAsStr().toLowerCase();
				String[] quoteArr = quote.split("\\s+");
				
				// for each string in quote, check if any query strings match. increment if so
				for (String quoteStr : quoteArr) {
					for (String queryStr : queryArr) {
						if (quoteStr.equals(queryStr)) {
							currMatchScore++;
						}
					}	
				}
				
				if (currMatchScore != 0 && currMatchScore > maxMatchScore) {
					maxMatchScore = currMatchScore;
					maxMatchIdx = i;
				}
			}
			
			if (maxMatchIdx == -1) {
				return db.getDB().get(catIdx).get(new Random().nextInt(db.getDB().get(catIdx).size()));
			} else { // if there was no keyword matching, return a random entry from this category
				return db.getDB().get(catIdx).get(maxMatchIdx);
			}
			
		} else { // perform a pure keyword maximization search on all entries in the db if not category information available
			int maxMatchScore = -1;
			int[] maxMatchIdx = new int[2];
			maxMatchIdx[0] = maxMatchIdx[1] = -1;
			
			// iterate over all of the categories
			for (int cat_iter = 0; cat_iter < RelationalDBUtil.NUM_CATEGORIES; cat_iter++) {
				
				// iterate over all of the entries in each individual category
				for (int i = 0; i < db.getDB().get(cat_iter).size(); i++) {
					int currMatchScore = 0;
					
					// split up the quote into tokens
					String quote = db.getDB().get(cat_iter).get(i).getQuote().getQuoteAsStr().toLowerCase();
					String[] quoteArr = quote.split("\\s+");
					
					// for each string in quote, check if any query strings match. increment if so
					for (String quoteStr : quoteArr) {
						for (String queryStr : queryArr) {
							if (quoteStr.equals(queryStr)) {
								currMatchScore++;
							}
						}	
					}
					
					if (currMatchScore != 0 && currMatchScore > maxMatchScore) {
						maxMatchScore = currMatchScore;
						maxMatchIdx[0] = cat_iter; maxMatchIdx[1] = i;
					}	
				}	
			}
			
			if (maxMatchIdx[0] == -1 || maxMatchIdx[1] == -1) {
				return NO_MATCH_RESPONSE;
			} else {
				return db.getDB().get(maxMatchIdx[0]).get(maxMatchIdx[1]);
			}
		}
	}
	
	private static final String SPACE = " ";
	private static final String NA = "N/A";
	private static final String OF = "of";
	private static final String NO_MATCH_RESPONSE_STR = "Dearest apologies, I don't seem to have an adequate answer to your inquiry.";
	private static final TextItem NO_MATCH_RESPONSE = new TextItem(new Quote(NO_MATCH_RESPONSE_STR), Category.BLANK, new Source(NA));
}