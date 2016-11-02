package models.db;

import java.util.ArrayList;

public class RelationalDB {
	
	/**
	 * the database will be setup as a 2D ArrayList with each column representing an outer
	 * level category (defined by the Category enum class)
	 * 
	 * Its indices will be set up in the order of the Category enum class as optimization (see static string at bottom)
	 * 
	 */
	private ArrayList<ArrayList<TextItem>> db;
	
	public RelationalDB(ArrayList<ArrayList<TextItem>> db) {
		this.db = db;
	}
	
	/**
	 * Method uses the TextItem param to determine the primary Category and
	 * queries the hash for the index of this category in the db
	 * 
	 * @param textItem
	 */
	public void addTextItem(TextItem textItem) {
		db.get((int)RelationalDBUtil.catDescr2idx2Cat.get(textItem.getCategory().getCatName()).get(RelationalDBUtil.DBVALUE_IDX)).add(textItem);
	}
}
