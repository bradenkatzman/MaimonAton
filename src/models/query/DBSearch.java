package models.query;

import models.db.RelationalDB;
import models.db.RelationalDBUtil;
import models.db.TextItem;

public class DBSearch {
	
	private RelationalDB db;
	
	public DBSearch(RelationalDB db) {
		this.db = db;
	}
	
	public TextItem naiveSearch(String query) {
		for (int i = 0; i < RelationalDBUtil.NUM_CATEGORIES; i++) {
			for (TextItem t : db.getDB().get(i)) {
				if (t.getQuote().getQuoteAsStr().toLowerCase().contains(query.toLowerCase())) {
					return t;
				}
			}
		}
		return null;
	}
}