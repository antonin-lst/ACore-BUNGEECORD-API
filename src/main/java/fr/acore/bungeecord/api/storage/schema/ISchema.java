package fr.acore.bungeecord.api.storage.schema;

import fr.acore.bungeecord.api.storage.database.IDatabase;
import fr.acore.bungeecord.api.storage.table.ITable;

import java.sql.Connection;
import java.util.List;

public interface ISchema<T extends ITable> {
	
	public IDatabase<?> getDataBase();
	public Connection getConnection();
	
	public String getName();
	
	public void load();
	public void save();
	
	public List<T> getTables();
	public T getTable(String name);
	public void addTable(T table);
	
	public void createTable(Class<?> tableClazz);
	public void createTable(T table);
	public void drop(String tableName);
	public void drop();

	//public T replace(ITable<?> target, ITable<?> newest);
	
	
	
}
