package util;

public class QueryStringReplacer {

	private static final String QUESTION_MARK = "[?]";
	
	public static String queryReplaceFirst(String query, String field) {
		return query.replaceFirst(QUESTION_MARK, field);
	}
	
	private QueryStringReplacer() {
		
	}

}
