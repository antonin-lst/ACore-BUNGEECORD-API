package fr.acore.bungeecord.api.storage.constraint.query;

import fr.acore.bungeecord.api.storage.constraint.ConstraintType;

public enum QueryConstraintType implements ConstraintType {
	
	WHERE,
	LIKE;

	@Override
	public String getSqlType() {
		return name();
	}

}
