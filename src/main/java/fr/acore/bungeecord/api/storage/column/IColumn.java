package fr.acore.bungeecord.api.storage.column;


import fr.acore.bungeecord.api.storage.column.type.ColumnType;
import fr.acore.bungeecord.api.storage.table.ITable;
import fr.acore.bungeecord.api.storage.utils.CustomSize;

import java.lang.reflect.Field;

public interface IColumn {
	
	public ITable getTable();
	
	public String getName();
	public ColumnType getType();
	public CustomSize getSize();

	public boolean isPrimary();
	public boolean isForeign();
	public String getForeign();
	public boolean isNullable();
	public String defaultValue();
	public boolean isUnique();
	public boolean isAutoIncrement();
	
	public Field getInjectedField();
	public void injectField(Field columnField);
	public boolean isInjected();
	
	public <T> Object values(T obj);
	
	public default String toSql() {
		StringBuilder tableToSql = new StringBuilder(getName());
		tableToSql.append(" ").append(getType().getSqlType()).append("(").append(getSize().toSql() + ")");

		if(!isNullable()) tableToSql.append(" NOT NULL");
		if(isAutoIncrement() && getType().equals(ColumnType.INTEGER) && isPrimary()) tableToSql.append(" AUTO_INCREMENT");
		return tableToSql.toString();
	}

}
