package view.UI;


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
	
	public static String formatConvPanelStr(String str) {
		return formatStr(str, CONV_PANEL_LINE_LENGTH);
	}
	
	/*
	 * TODO
	 * if this is mid-word, add a '-'
	 */
	private static String formatStr(String str, int line_length) {
		if (str.length() <= line_length || str.length() <= 1) return str;
		
		String str_formatted = "";
		
		int lastIdx = 0;
		for (int i = 0; i < str.length(); i++) {
			if ((i+1) % line_length == 0 || (i+1) == str.length()) {
				str_formatted += NEW_LINE + str.substring(lastIdx, i+1);
				lastIdx = i+1;
			}
		}
		
		return str_formatted;
	}
	
	private static final int RESP_PANEL_LINE_LENGTH = 30;
	private static final int CONV_PANEL_LINE_LENGTH = 50;
	private static final String NEW_LINE = "\n";
}
