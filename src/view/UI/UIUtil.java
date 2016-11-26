package view.UI;

import java.util.ArrayList;

/**
 * This class serves as a utility class for basic functions that are needed for the UI.
 * These methods include:
 * 
 * 
 * 
 * @author braden
 *
 */
public class UIUtil {

	
	public static String formatRespPanelStr(String str) { 
		return formatStr(str, RESP_PANEL_LINE_LENGTH);
	}
	
	public static String formatConvPanelStr(ArrayList<String> convo) {
		String formatted_str = "";
		
		for (String str : convo) {
			formatted_str += (formatStr(str, CONV_PANEL_LINE_LENGTH) + NEW_LINE);
		}
		
		return formatted_str;
	}
	
	private static String formatStr(String str, int line_length) {
		if (str.length() < 2) return str;
		
		String formatted_str = "";
		int lastIdx = 0;
		for (int i = 1; i < str.length(); i++) {
			if (i % line_length == 0) {
				if (i != str.length()-1 && !Character.isSpaceChar(str.charAt(i))) {
					// we need to add a dash to indicate continuation of a word on the next line
					formatted_str += (str.substring(lastIdx, i+1) + DASH + NEW_LINE);
					lastIdx = i+1;
				} else { // we got lucky and the mod 0 came at a space character
					formatted_str += (str.substring(lastIdx, i+1) + NEW_LINE);
					lastIdx = i+1;
				}
			}
		}
		
		formatted_str += (str.substring(lastIdx) + NEW_LINE);
		
		return formatted_str;
	}
	
	
	private static final int RESP_PANEL_LINE_LENGTH = 27;
	private static final int CONV_PANEL_LINE_LENGTH = 75;
	private static final String NEW_LINE = "\n";
	private static final String DASH = "-";
}