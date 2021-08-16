package fr.acore.bungeecord.module;

import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.manager.IManagerCollection;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.version.Version;
import fr.acore.bungeecord.config.Setupable;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.util.List;

public class AManager extends Setupable implements IManager, IManagerCollection<AManager> {

	private AModule module;
	
	public AManager(AModule key, boolean useConfig) {
		super(key, useConfig);
		this.module = key;
	}
	
	/*
	 * 
	 * Method Override si utilisation de la config
	 * 
	 */
	@Override
	public void setup(Configuration config) {
		
	}
	
	/*
	 * 
	 * Method herit�e de IManager
	 * 
	 */
	
	@Override
	public IPlugin<?> getPlugin() {
		return this.getKey();
	}
	
	/*
	 * 
	 * Extention du plugin enregistr�e void AModule
	 * 
	 */
	

	public String getServerName() {
		return key.getPluginName();
	}
	

	public String getPluginName() {
		return key.getPluginName();
	}
	

	public Version getPluginVersion() throws Version.ParseVersionException {
		return key.getPluginVersion();
	}

	

	public File getConfigFile() {
		return key.getConfigFile();
	}
	

	public Configuration getConfig() {
		return key.getConfig();
	}
	

	public void loadCustomConfig() {
		key.loadCustomConfig();
	}
	

	public void reloadConfig() {
		key.reloadConfig();
	}


	public List<AManager> getInternalManagers() {
		return module.getInternalManagers();
	}

	@Override
	public <T extends IManager> T getInternalManager(Class<T> clazz) {
		return null;
	}

	@Override
	public <T extends IManager> T getManager(Class<T> clazz) {
		return null;
	}

	@Override
	public void registerManager(AManager manager) {

	}

	@Override
	public void unregisterManager(AManager manager) {

	}



	public void registerSetupable(ISetupable<IPlugin<?>> setupable) {
		key.registerSetupable(setupable);
	}
	

	public void unregisterSetupable(ISetupable<IPlugin<?>> setupable) {
		key.unregisterSetupable(setupable);
	}
	

	public Listener registerListener(Listener listener) {
		return key.registerListener(listener);
	}
	

	public void unregisterAllListeners() {
		key.unregisterAllListeners();
	}
	

	public void callEvent(Event event) {
		key.callEvent(event);
	}
	

	public long getStartMillis() {
		return key.getStartMillis();
	}
	
	/*
	 * 
	 * Gestion des logs
	 * 
	 */
	
	@Override
	public void log(String... args) {
		key.log(args);
	}

	@Override
	public void logWarn(String... args) {
		key.logWarn(args);
	}

	@Override
	public void logErr(String... args) {
		key.logErr(args);
	}

	@Override
	public void log(Object... args) {
		key.log(args);
	}

	@Override
	public void logWarn(Object... args) {
		key.logWarn(args);
	}

	@Override
	public void logErr(Object... args) {
		key.logErr(args);
	}

}
