package com.tjeannin.provigen;

public class Constraint {

	/**
	 * A UNIQUE constraint is similar to a PRIMARY KEY constraint, except that a single table may have any number of UNIQUE constraints.
	 * For each UNIQUE constraint on the table, each row must feature a unique combination of values in the columns identified by the UNIQUE constraint.
	 * As with PRIMARY KEY constraints, for the purposes of UNIQUE constraints NULL values are considered distinct from all other values (including other NULLs).
	 * If an INSERT or UPDATE statement attempts to modify the table content so that two or more rows feature identical values in a set of columns that are subject to a UNIQUE constraint, it is a constraint violation.
	 */
	public static final String UNIQUE = "UNIQUE";

	/**
	 * A NOT NULL constraint may only be attached to a column definition, not specified as a table constraint.
	 * Not surprisingly, a NOT NULL constraint dictates that the associated column may not contain a NULL value.
	 * Attempting to set the column value to NULL when inserting a new row or updating an existing one causes a constraint violation.
	 */
	public static final String NOT_NULL = "NOT NULL";

	public class OnConflict {

		/**
		 * When an applicable constraint violation occurs, the ROLLBACK resolution algorithm aborts the current SQL statement with an SQLITE_CONSTRAINT error and rolls back the current transaction.
		 * If no transaction is active (other than the implied transaction that is created on every command) then the ROLLBACK resolution algorithm works the same as the ABORT algorithm.
		 */
		public static final String ROLLBACK = "ROLLBACK";

		/**
		 * When an applicable constraint violation occurs, the ABORT resolution algorithm aborts the current SQL statement with an SQLITE_CONSTRAINT error and backs out any changes made by the current SQL statement;
		 * but changes caused by prior SQL statements within the same transaction are preserved and the transaction remains active.
		 * This is the default behavior and the behavior specified by the SQL standard.
		 */
		public static final String ABORT = "ABORT";

		/**
		 * When an applicable constraint violation occurs, the FAIL resolution algorithm aborts the current SQL statement with an SQLITE_CONSTRAINT error.
		 * But the FAIL resolution does not back out prior changes of the SQL statement that failed nor does it end the transaction.
		 * For example, if an UPDATE statement encountered a constraint violation on the 100th row that it attempts to update,
		 * then the first 99 row changes are preserved but changes to rows 100 and beyond never occur.
		 */
		public static final String FAIL = "FAIL";

		/**
		 * When an applicable constraint violation occurs, the IGNORE resolution algorithm skips the one row that contains the constraint violation and continues processing subsequent rows of the SQL statement as if nothing went wrong.
		 * Other rows before and after the row that contained the constraint violation are inserted or updated normally.
		 * No error is returned when the IGNORE conflict resolution algorithm is used.
		 */
		public static final String IGNORE = "IGNORE";

		/**
		 * When a UNIQUE constraint violation occurs, the REPLACE algorithm deletes pre-existing rows that are causing the constraint violation prior to inserting or updating the current row and the command continues executing normally.
		 * If a NOT NULL constraint violation occurs, the REPLACE conflict resolution replaces the NULL value with he default value for that column, or if the column has no default value, then the ABORT algorithm is used.
		 * If a CHECK constraint violation occurs, the REPLACE conflict resolution algorithm always works like ABORT.
		 */
		public static final String REPLACE = "REPLACE";
	}

	private String type;
	private String onConflict;

	Constraint(String type, String onConflict) {
		super();
		this.type = type;
		this.onConflict = onConflict;
	}

	String getType() {
		return type;
	}

	void setType(String type) {
		this.type = type;
	}

	String getOnConflict() {
		return onConflict;
	}

	void setOnConflict(String onConflict) {
		this.onConflict = onConflict;
	}

	boolean isUnique() {
        return type != null && type.equals(UNIQUE);
    }

	boolean isNotNull() {
        return type != null && type.equals(NOT_NULL);
    }
}
