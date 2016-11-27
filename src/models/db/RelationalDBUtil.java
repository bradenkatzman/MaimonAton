package models.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public final class RelationalDBUtil {
	
	// make a hashtable that maps the category name to the index in the list
	public final static Hashtable<String, ArrayList<Object>> catDescr2idx2Cat;
	static {
		catDescr2idx2Cat = new Hashtable<String, ArrayList<Object>>();
		catDescr2idx2Cat.put("Mean", new ArrayList<>(Arrays.asList(0, Category.MEAN)));
		catDescr2idx2Cat.put("Virtue", new ArrayList<>(Arrays.asList(1, Category.VIRTUE)));
		catDescr2idx2Cat.put("Morality", new ArrayList<>(Arrays.asList(2, Category.MORALITY)));
		catDescr2idx2Cat.put("Negative Theology", new ArrayList<>(Arrays.asList(3, Category.NEGATIVE_THEOLOGY)));
		catDescr2idx2Cat.put("Biomedical Ethics", new ArrayList<>(Arrays.asList(4, Category.BIOMEDICAL_ETHICS)));
		catDescr2idx2Cat.put("Imitatio Dei", new ArrayList<>(Arrays.asList(5, Category.IMITATIO_DEI)));
		catDescr2idx2Cat.put("Knowledge", new ArrayList<>(Arrays.asList(6, Category.KNOWLEDGE)));
		catDescr2idx2Cat.put("Lashon Hara", new ArrayList<>(Arrays.asList(7, Category.LASHON_HARA)));
		catDescr2idx2Cat.put("Soul", new ArrayList<>(Arrays.asList(8, Category.SOUL)));
		catDescr2idx2Cat.put("Veils", new ArrayList<>(Arrays.asList(9, Category.VEILS)));
		catDescr2idx2Cat.put("Human Potential", new ArrayList<>(Arrays.asList(10, Category.HUMAN_POTENTIAL)));
		catDescr2idx2Cat.put("Choice", new ArrayList<>(Arrays.asList(11, Category.CHOICE)));
		catDescr2idx2Cat.put("Image of God", new ArrayList<>(Arrays.asList(12, Category.GODLY_IMAGE)));;
		catDescr2idx2Cat.put("Social", new ArrayList<>(Arrays.asList(13, Category.SOCIAL)));
		catDescr2idx2Cat.put("Political", new ArrayList<>(Arrays.asList(14, Category.POLITICAL)));
		catDescr2idx2Cat.put("Commandments", new ArrayList<>(Arrays.asList(15, Category.COMMANDMENTS)));
		catDescr2idx2Cat.put("System Justification", new ArrayList<>(Arrays.asList(16, Category.SYSTEM_JUSTIFICATION)));
		catDescr2idx2Cat.put("Vice", new ArrayList<>(Arrays.asList(17, Category.VICE)));
		catDescr2idx2Cat.put("N/A", new ArrayList<>(Arrays.asList(18, Category.BLANK)));
	}
	
	public final static void writeAddedSourcesToDBFile() {
		
	}
	
	public final static String RDB_FILE_PATH = "/models/db/texts_file/MaimonAton_db.tsv";
	public final static int DBVALUE_IDX = 0;
	public final static int CATEGORYVALUE_IDX = 1;
	public final static int NUM_CATEGORIES = 17;
}
