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
	private ArrayList<int[]> addedItems_idx; // we'll keep track of the indices of added items so that on program close, they can be written to file
	private boolean addedItems;
	
	public RelationalDB(ArrayList<ArrayList<TextItem>> db) {
		this.db = db;
		this.addedItems_idx = new ArrayList<int[]>();
		this.addedItems = false;
	}
	
	/**
	 * Method uses the TextItem param to determine the primary Category and
	 * queries the hash for the index of this category in the db. Also saves
	 * the location of the added item so that on program close these additions
	 * can be written to file if the user chooses
	 * 
	 * @param textItem
	 */
	public void addTextItem(TextItem textItem) {
		if (!addedItems) addedItems = true;
		int catIdx = (int)RelationalDBUtil.catDescr2idx2Cat.get(textItem.getCategory().getCatName()).get(RelationalDBUtil.DBVALUE_IDX);
		
		db.get(catIdx).add(textItem);
		
		int locationIdx = db.get(catIdx).size()-1;
		
		int[] addedItemLocation = new int[2];
		addedItemLocation[0] = catIdx;
		addedItemLocation[1] = locationIdx;
	}
	
	public String getStatistics() {
		int count = 0;
		
		for (ArrayList<TextItem> catList : db) {
			count += catList.size();
		}
		
		return String.valueOf(count);
	}
	
	public ArrayList<ArrayList<TextItem>> getDB() {
		return this.db;
	}
	
	public ArrayList<int[]> getAddedItemsIdx() {
		if (addedItems()) return this.addedItems_idx;
		
		return null;
	}
	
	public boolean addedItems() { return this.addedItems; }
}