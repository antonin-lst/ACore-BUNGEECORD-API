package fr.acore.bungeecord.storage.constraint.query;


import fr.acore.bungeecord.api.storage.constraint.query.column.ColumnQueryConstraintType;
import fr.acore.bungeecord.api.storage.constraint.query.column.IColumnQueryConstraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryColumnConstraint implements IColumnQueryConstraint {

	private ColumnQueryConstraintType columnConstraintType;
	private List<String> datas;
	
	public QueryColumnConstraint(ColumnQueryConstraintType columnConstraintType, String... datas) {
		this.columnConstraintType = columnConstraintType;
		this.datas = new ArrayList<>();
		if(datas != null) {
			this.datas.addAll(Arrays.asList(datas));
		}
	}
	
	
	@Override
	public ColumnQueryConstraintType getConstraintType() {
		return this.columnConstraintType;
	}

	@Override
	public List<Object> getDatas() {
		return Arrays.asList(datas.toArray(new Object[0]));
	}

	@Override
	public String toSql() {
		StringBuilder columnConstraint = new StringBuilder("");
		switch (this.columnConstraintType) {
		case SELECT:
			if(datas.isEmpty()) {
				columnConstraint.append("*");
			}else {
				int i = 1;
				for(String column : datas) {
					columnConstraint.append(column);
					if(i < datas.size()) columnConstraint.append(", ");
					i++;
				}
			}
			break;

		case UPDATE:
			if(datas.isEmpty()) {
				columnConstraint.append("*");
			}else {
				int i = 1;
				for(String column : datas) {
					columnConstraint.append(column + " = ?");
					if(i < datas.size()) columnConstraint.append(", ");
					i++;
				}
			}
			break;
		}
		return columnConstraint.toString();
	}

}
