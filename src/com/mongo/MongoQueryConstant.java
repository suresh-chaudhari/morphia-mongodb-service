package com.mongo;

/**
 * 
 * @author suresh
 *
 */
public class MongoQueryConstant {

	public static final String LESS_THAN = "$lt";
	public static final String LESS_THAN_EQUAL = "$lte";
	public static final String GREATER_THAN = "$gt";
	public static final String GREATER_THAN_EQUAL = "$gte";
	public static final String NOT_EQUAL = "$ne";
	public static final String EQUAL = "$eq";
	public static final String IN = "$in";
	public static final String NIN = "$nin"; //Specifies an array of unwanted matches
	public static final String ALL = "$all"; //array value must match the condition
	public static final String SET = "$set";
	public static final String WHERE = "$where";
	public static final String MODULO = "$mod";
	public static final String EXISTS = "$exists";
	public static final String REGEX = "$regex";
	public static final String PUSH = "$push";
	public static final String EACH = "$each";
	public static final String ADD_TO_SET = "$addToSet";
	public static final String GROUP = "$group";
	public static final String UNWIND = "$unwind";
	public static final String SUM = "$sum";
	public static final String COUNT = "$count";
	public static final String SORT = "$sort";
	public static final String INC = "$inc";
	public static final String MATCH = "$match";
	public static final String PROJECT = "$project";
	public static final String SKIP = "$skip";
	public static final String LIMIT = "$limit";
	public static final String ELEMENT_MATCH = "$elemMatch";

	//Logical Operator
	public static final String AND = "$and";
	public static final String OR = "$or";
	public static final String NOR = "$nor";
	public static final String NOT = "$not";//Inverts the effect of a query expression and returns documents that do not match the query expression


	public enum OPERATOR {
		EQUAL("="),
		NOTE_EQUAL("<>"),
		GREATER_THAN(">"),
		LESS_THAN("<"),
		GREATER_THAN_EQUAL(">="),
		LESS_THAN_EQUAL("<="),
		IN("in"),
		NIN("nin"),
		ALL("all"),
		SIZE("size"),
		EXIST("exist"),
		;

		private final String name;

		private OPERATOR(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

}
