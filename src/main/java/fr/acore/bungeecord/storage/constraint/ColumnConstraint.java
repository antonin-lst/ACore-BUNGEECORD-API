package fr.acore.bungeecord.storage.constraint;


import fr.acore.bungeecord.api.storage.constraint.Constraint;
import fr.acore.bungeecord.storage.constraint.column.ColumnConstraintType;

import java.util.List;

public class ColumnConstraint implements Constraint<ColumnConstraintType> {

	private ColumnConstraintType constraintType;
	
	public ColumnConstraint(ColumnConstraintType constraintType) {
		this.constraintType = constraintType;
	}
	
	@Override
	public String toSql() {
		return constraintType.getSqlType();
	}

	@Override
	public ColumnConstraintType getConstraintType() {
		return this.constraintType;
	}
	
	@Override
	public List<Object> getDatas() {
		// TODO Auto-generated method stub
		return null;
	}

}
