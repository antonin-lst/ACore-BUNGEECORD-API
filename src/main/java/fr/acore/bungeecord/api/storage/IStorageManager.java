package fr.acore.bungeecord.api.storage;


import fr.acore.bungeecord.api.storage.database.IDatabase;
import fr.acore.bungeecord.api.storage.exception.DBExistsException;
import fr.acore.bungeecord.api.storage.exception.DBNotFoundException;
import fr.acore.bungeecord.api.storage.factory.IDataFactory;

import java.util.List;

public interface IStorageManager {
	
	/*
	 * 
	 * Chargement et sauvegarde
	 * 
	 */
	
	public void load();
	public void save();
	
	/*
	 * 
	 * Gestion des databases
	 * 
	 */
	
	public void addDatabase(IDatabase<?> db) throws DBExistsException;
	
	public List<IDatabase<?>> getDatabases();
	public IDatabase<?> getDatabase(String name) throws DBNotFoundException;
	
	public IDatabase<?> getDefaultDatabase();
	public void setDefaultDatabase(String name) throws DBNotFoundException;

	/*
	 * 
	 * 
	 * Gestion de la population des tables
	 * 
	 */
	
	public List<IDataFactory<?, ?>> getDataFactories();
	
	public void addDataFactory(IDataFactory<?, ?> dataFactory);
	public void removeDataFactory(IDataFactory<?, ?> dataFactory);
	
}
