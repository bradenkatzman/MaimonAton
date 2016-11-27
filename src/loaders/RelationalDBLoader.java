package loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import models.db.Category;
import models.db.Quote;
import models.db.RelationalDBUtil;
import models.db.Source;
import models.db.TextItem;

public class RelationalDBLoader {
	
	private static final String HEADER_LINE = "Quote	Categories	Source	Other Sources";
	private static final int NUM_FIELDS = 4;
	
	public static ArrayList<ArrayList<TextItem>> buildRelationalDB() {
		System.out.print("Building RelationalDB");
		final URL url = RelationalDBLoader.class.getResource(RelationalDBUtil.RDB_FILE_PATH);
		
		ArrayList<ArrayList<TextItem>> db = new ArrayList<ArrayList<TextItem>>();
		/* initialize an ArrayList for each category */
		for (int i = 0; i <= RelationalDBUtil.NUM_CATEGORIES; i++) {
			db.add(new ArrayList<TextItem>());
		}
		
		try (InputStream stream = url.openStream();
	             InputStreamReader streamReader = new InputStreamReader(stream);
	             BufferedReader reader = new BufferedReader(streamReader)) {
				
				String line;
				
				while ((line = reader.readLine()) != null) {
					if (line.equals(HEADER_LINE)) {
						line = reader.readLine();
					
						if (line == null) {
							break;
						}
					}
					
					StringTokenizer tokenizer = new StringTokenizer(line, "	");
					
					if (tokenizer.countTokens() == NUM_FIELDS) {
						Quote q = new Quote(tokenizer.nextToken());
						
						Category c;
						String category_token = tokenizer.nextToken();
						if (category_token.contains(period)) {
							StringTokenizer t = new StringTokenizer(category_token, period);
							c = (Category)RelationalDBUtil.catDescr2idx2Cat.get(t.nextToken()).get(RelationalDBUtil.CATEGORYVALUE_IDX);
							while(t.hasMoreTokens()) {
								c.addSubCat(t.nextToken());
							}						
						} else {
							c = (Category)RelationalDBUtil.catDescr2idx2Cat.get(category_token).get(RelationalDBUtil.CATEGORYVALUE_IDX);
						}
						
						Source s;
						String source_token = tokenizer.nextToken();
						String source_name;
						String page_number = null;
						boolean hasPageNum = false;
						if (source_token.contains(period)) {
							hasPageNum = true;
							StringTokenizer t = new StringTokenizer(source_token, period);
							source_name = t.nextToken();
							page_number = t.nextToken();
						} else {
							source_name = source_token;
						}
						
						ArrayList<String> other_sources = new ArrayList<String>();
						String other_sources_token = tokenizer.nextToken();
						if (other_sources_token.contains(period)) {
							StringTokenizer t = new StringTokenizer(other_sources_token, period);
							while (t.hasMoreTokens()) {
								other_sources.add(t.nextToken());
							}
						} else {
							other_sources.add(other_sources_token);
						}
						
						if (hasPageNum) {
							s = new Source(source_name, page_number, other_sources);
						} else {
							s = new Source(source_name, other_sources);
						}
						
						TextItem tItem = new TextItem(q, c, s);

						db.get((int)RelationalDBUtil.catDescr2idx2Cat.get(c.getCatName()).get(RelationalDBUtil.DBVALUE_IDX)).add(tItem);
					} else {
						System.out.println("\nProblem processing field: " + line);
						System.out.println("Num of fields in error line = " + tokenizer.countTokens());
					}
				}
			
		} catch(IOException e) {
			System.out.println("The file at " + RelationalDBUtil.RDB_FILE_PATH + " was not found.");
		}
		
		System.out.print(" --> complete\n");
		return db;
	}
	
	private final static String period = ".";
}