package fr.acore.bungeecord.config.utils;


import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.storage.database.driver.DatabaseDriver;
import fr.acore.bungeecord.config.Setupable;
import net.md_5.bungee.config.Configuration;


public class Conf extends Setupable {

	//private ILogger logger = LoggerManager.getLogger();

	private static String redisHost = "127.0.0.1";
	public static String getJedisHost() { return redisHost;}
	private static int redisPort = 6379;
	public static int getJedisPort() { return redisPort;}
	private static String redisPassword = "";
	public static String getJedisPassword() { return redisPassword;}

	/*
	 * Storage
	 * configuration
	 */
	private static boolean useStorage;
	public static boolean isUseStorage() { return useStorage;}
	private static DatabaseDriver storageType;
	public static DatabaseDriver getStorageType() { return storageType == null ? DatabaseDriver.JSON : storageType;}
	//sql
	private static String urlbase;
	public static String getUrlBase() { return urlbase;}
	private static String host;
	public static String getHost() { return host;}
	private static String database;
	public static String getDatabase() { return database;}
	private static String user;
	public static String getUser() { return user;}
	private static String pass;
	public static String getPass() { return pass;}

	public Conf(IPlugin plugin) {
		super(plugin, true);
	}

	@Override
	public void setup(Configuration configuration) {
		redisHost = configuration.getString("redis.host");
		redisPort = configuration.getInt("redis.port");
		redisPassword = configuration.getString("redis.password");

		useStorage = configuration.getBoolean("storage.enable");
		storageType = DatabaseDriver.valueOf(configuration.getString("storage.type"));
		urlbase = configuration.getString("storage.sql.urlbase");
		host = configuration.getString("storage.sql.host");
		database = configuration.getString("storage.sql.database");
		user = configuration.getString("storage.sql.user");
		pass = configuration.getString("storage.sql.pass");
	}

}

