package fr.acore.bungeecord.api.storage.database.driver;

public enum DatabaseDriver {
	
	MYSQL("jdbc:mysql://"),
	SQLITE("jdbc:sqlite:"),
	JSON("");
	
	private String driver;
	
	DatabaseDriver(String driver) {
		this.driver = driver;
	}
	
	public String getDriver() {
		return driver;
	}

}
