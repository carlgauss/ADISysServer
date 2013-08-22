package utility;

public class QueryStringReplacer {

    private static final String QUESTION_MARK = "[?]";
    private static final String APOSTROPHE = "[']";

    public static String queryReplaceFirst(String query, String field) {
        field = field.replaceAll(APOSTROPHE, "''");
        return query.replaceFirst(QUESTION_MARK, field);
    }

    private QueryStringReplacer() {

    }

}
