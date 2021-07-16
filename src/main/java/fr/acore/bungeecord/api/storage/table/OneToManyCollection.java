package fr.acore.bungeecord.api.storage.table;


import fr.acore.bungeecord.api.storage.column.foreign.OneToMany;

import java.lang.reflect.Field;

public class OneToManyCollection {
	
	private Field collectionField;
	private OneToMany oneToMany;
	
	public OneToManyCollection(Field collectionField, OneToMany oneToMany) {
		this.collectionField = collectionField;
		this.oneToMany = oneToMany;
	}
	
	public Field getCollectionField() {
		return collectionField;
	}
	
	public OneToMany getOneToMany() {
		return oneToMany;
	}

}
