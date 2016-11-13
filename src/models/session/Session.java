package models.session;

import java.util.ArrayList;

public class Session {
	
	private String userName;
	private ArrayList<String> conversation;
	private String userName_formatted;
	
	
	/**
	 * Constructor for starting a new conversation
	 * 
	 * @param userName
	 */
	public Session(String userName) {
		this.userName = userName;
		this.conversation = new ArrayList<String>();
		this.userName_formatted = formatName(this.userName);
		
	}
	
	/**
	 * Constructor for loading a session
	 * 
	 * @param userName
	 * @param conversation
	 */
	public Session(String userName, ArrayList<String> conversation) {
		this.userName = userName;
		this.conversation = conversation;
	}
	
	/**
	 * 
	 * TODO
	 * when this is working basically, 
	 * the response should be change to a more robust item that can handle clicking for more info and whatnot. will be a separate class of its own
	 * the query should also be more robust with things like key words of the question, question type (even if its question or not) and more sophisticated
	 * classifications for better querying
	 * 
	 * @param query
	 */
	public void addInteraction(String query, String response) {
		conversation.add(userName_formatted + query);
		conversation.add(MAIMONATON_formatted + response);
	}
	
	private String formatName(String name) {
		return name + COLON + SPACE;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public ArrayList<String> getConversation() {
		return this.conversation;
	}
	
	
	// access start up script on creation
	
	
	private final static String COLON = ":";
	private final static String SPACE = " ";
	private final static String MAIMONATON_formatted = "MaimonAton" + COLON + SPACE;

}
