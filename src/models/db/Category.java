package models.db;

import java.util.ArrayList;

public enum Category {
	
	MEAN("Mean"),
	
	VIRTUE("Virtue"),
	
	MORALITY("Morality"),
	
	NEGATIVE_THEOLOGY("Negative Theology"),
	
	BIOMEDICAL_ETHICS("Biomedical Ethics"),
	
	IMITATIO_DEI("Imitatio Dei"),
	
	KNOWLEDGE("Knowledge"),
	
	LASHON_HARA("Lashon Hara"),
	
	SOUL("Soul"),
	
	VEILS("Veils"),
	
	HUMAN_POTENTIAL("Human Potential"),
	
	CHOICE("Choice"),
	
	GODLY_IMAGE("Image of God"),
	
	SOCIAL("Social"),
	
	POLITICAL("Political"),
	
	COMMANDMENTS("Commandments"),
	
	SYSTEM_JUSTIFICATION("System Justification"),
	
	VICE("Vice"),
	
	BLANK("N/A");
	
	private final String cat_name;
	private final ArrayList<String> subcats;
	
	Category(String cat_name) {
		this.cat_name = cat_name;
		this.subcats = new ArrayList<String>();
	}
	
	public void addSubCat(String sub) {
		subcats.add(sub);
	}
	
	public String getCatName() {
		return this.cat_name;
	}
	
	public ArrayList<String> getSubCats() {
		return this.subcats;
	}
	
	@Override
	public String toString() {
		String str = this.cat_name;
		
		for (String s : subcats) {
			str += (CS + s);
		}
		
		return str;
		
	}
	
	private final static String CS = ", ";
}