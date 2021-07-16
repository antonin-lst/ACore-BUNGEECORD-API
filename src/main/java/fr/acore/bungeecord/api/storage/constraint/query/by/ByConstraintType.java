package fr.acore.bungeecord.api.storage.constraint.query.by;


import fr.acore.bungeecord.api.storage.constraint.ConstraintType;

public enum ByConstraintType implements ConstraintType {

	GROUP_BY("GROUP BY"),
	ORDER_BY("ORDER BY");
	
	private String sqlType;
	
	ByConstraintType(String sqlType){
		this.sqlType = sqlType;
	}
	
	public String getSqlType() {
		return sqlType;
	}
	
}
