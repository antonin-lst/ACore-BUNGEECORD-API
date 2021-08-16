package fr.acore.bungeecord.storage;


import fr.acore.bungeecord.api.storage.column.Column;
import fr.acore.bungeecord.api.storage.table.Table;

import java.lang.reflect.Field;

public class DBHelper {
	
	public static String getRealColumnName(Field columnField) {
		return columnField.getDeclaredAnnotation(Column.class) != null ? columnField.getDeclaredAnnotation(Column.class).columnName() : columnField.getName();
	}
	
	public static String getRealClassName(Class<?> clazz) {
		return clazz.getDeclaredAnnotation(Table.class) != null	? clazz.getDeclaredAnnotation(Table.class).name() : clazz.getSimpleName();
	}
	
}
