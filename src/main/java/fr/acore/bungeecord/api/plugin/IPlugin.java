package fr.acore.bungeecord.api.plugin;


import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.logger.ILogger;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.manager.IManagerCollection;
import fr.acore.bungeecord.api.version.Version;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;

import java.io.File;

public interface IPlugin<T extends IManager> extends IManagerCollection<T>, ILogger {
	
	/*
	 * Plugin information (ServerName, PluginName, PluginVersion)
	 * 
	 */
	
	public String getServerName();
	public String getPluginName();
	public Version getPluginVersion() throws Version.ParseVersionException;
	
	/*
	 * fileConfiguration (yaml format)
	 * 
	 */
	
	public File getConfigFile();
	public Configuration getConfig();
	public void loadCustomConfig();
	public void reloadConfig();
	
	public long getStartMillis();
	
	/*
	 * 
	 * Gestion de la configuration
	 * 
	 */
	
	public void registerSetupable(ISetupable<IPlugin<?>> setupable);
	public void unregisterSetupable(ISetupable<IPlugin<?>> setupable);
	
	/*
	 * 
	 * gestion des listener
	 * 
	 */
	
	public Listener registerListener(Listener listener);
	public void unregisterAllListeners();
	public void callEvent(Event event);


	/*
	 * 
	 * Gestion des DataFactory
	 * 
	 */
	
	//public void registerDataFactory(IDataFactory<?,?> factory);
	
	
	/*
	 * 
	 * Gestion des joueurs
	 * 
	 * 
	 */
	
	//public List<OfflineCorePlayer> getOfflineCorePlayers();
	//public List<CorePlayer<?>> getCorePlayer();
	
	//public OfflineCorePlayer getOfflineCorePlayer(OfflinePlayer player);
	//public CorePlayer<?> getCorePlayer(Player player);

}
