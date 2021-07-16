package fr.acore.bungeecord.api.storage.database;


import fr.acore.bungeecord.api.storage.database.driver.DatabaseDriver;
import fr.acore.bungeecord.api.storage.database.exception.ConnectionException;
import fr.acore.bungeecord.api.storage.database.exception.NotConnectedException;
import fr.acore.bungeecord.api.storage.exception.schema.SchemaNotFounException;
import fr.acore.bungeecord.api.storage.schema.ISchema;

import java.sql.Connection;
import java.util.List;

public interface IDatabase<T extends ISchema<?>> {
	
	public String getHost();
	public void setHost(String host);
	public int getPort();
	public void setPort(int port);
	public DBUser getDbUser();
	public void setDbUser(DBUser user);
	public void connect() throws ConnectionException;
	public void disconect() throws NotConnectedException;
	public boolean isConnected();
	public Connection getConnection();
	
	public void load();
	public void save();
	
	public DatabaseDriver getDriver();
	public String getName();
	
	public List<T> getSchemas();
	public T getSchema(String name) throws SchemaNotFounException;
	public void addSchema(T schema);
	public void addSchema(String name);
	
	public void setDefaultSchema(String name) throws SchemaNotFounException;
	public T getDefaultSchema();

}
