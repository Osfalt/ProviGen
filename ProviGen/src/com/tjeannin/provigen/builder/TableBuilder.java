package com.tjeannin.provigen.builder;

import android.database.sqlite.SQLiteDatabase;
import com.tjeannin.provigen.Constraint;
import com.tjeannin.provigen.ContractHolder;
import com.tjeannin.provigen.DatabaseField;
import com.tjeannin.provigen.InvalidContractException;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {

    private ContractHolder contractHolder;
    private List<Constraint> constraints;

    public TableBuilder(Class contractClass) throws InvalidContractException {
        contractHolder = new ContractHolder(contractClass);
        constraints = new ArrayList<Constraint>();
    }

    public TableBuilder addConstraint(String constraintColumn, String constraintType, String constraintConflictClause) {
        constraints.add(new Constraint(constraintColumn, constraintType, constraintConflictClause));
        return this;
    }

    public String getSQL() {

        StringBuilder builder = new StringBuilder("CREATE TABLE ");
        builder.append(contractHolder.getTable()).append(" ( ");

        for (DatabaseField field : contractHolder.getFields()) {
            builder.append(" ").append(field.getName()).append(" ").append(field.getType());
            if (field.getName().equals(contractHolder.getIdField())) {
                builder.append(" PRIMARY KEY AUTOINCREMENT ");
            }
            for (Constraint constraint : constraints) {
                if (constraint.targetColumn.equals(field.getName())) {
                    builder.append(" ").append(constraint.type).append(" ON CONFLICT ").append(constraint.conflictClause);
                }
            }
            builder.append(", ");
        }
        builder.deleteCharAt(builder.length() - 2);
        builder.append(" ) ");
        return builder.toString();
    }

    public void createTable(SQLiteDatabase database) {
        database.execSQL(getSQL());
    }
}