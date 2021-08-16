package fr.acore.bungeecord.module;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.manager.Informable;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.plugin.module.IModule;
import fr.acore.bungeecord.api.storage.factory.IDataFactory;
import fr.acore.bungeecord.api.version.Version;
import fr.acore.bungeecord.config.Setupable;
import fr.acore.bungeecord.module.manager.AModuleManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AModule extends Plugin implements IModule, IPlugin<AManager> {

	/*
	 * 
	 * Instance de l'api
	 * 
	 */
	private ACoreBungeeCordAPI instance;
	
	/*
	 * 
	 * Gestion de la licence par module
	 * 
	 */
	
	private boolean licenceChecked;
	private boolean licenceValid;
	
	/*
	 * Plugin information (ServerName, PluginName, PluginVersion)
	 * 
	 */
	
	public String getServerName() { return new File("").getAbsoluteFile().getName();}
	public String getPluginName() { return getClass().getSimpleName();}
	public Version getPluginVersion() throws Version.ParseVersionException { return Version.fromString(getDescription().getVersion());}
	public Version getApiVersion() throws Version.ParseVersionException { return instance.getPluginVersion();}

	/*
	 * fileConfiguration (yaml format)
	 * 
	 */
	
	private File configFile = new File(getDataFolder() + File.separator + "config.yml");
	public File getConfigFile() { return this.configFile;}
	private Configuration config;
	public Configuration getConfig() { return this.config;}
	
	/*
	 * IManager list par module
	 * 
	 */
	private List<AManager> managers;
	
	
	
	@Override
	public void onEnable() {
		this.instance = ACoreBungeeCordAPI.getInstance();
		this.managers = new ArrayList<>();
		loadCustomConfig();
		getManager(AModuleManager.class).addModule(this);
	}
	
	
	@Override
	public void onDisable() {
		
	}
	
	/*
	 * 
	 * Gestion de l'etat de la licence
	 * 
	 */
	
	@Override
	public boolean isValidLicence() {
		return this.licenceValid;
	}
	
	@Override
	public boolean isLicenceChecked() {
		return this.licenceChecked;
	}

	@Override
	public void setLicenceChecked() {
		this.licenceChecked = true;
	}
	
	@Override
	public void setValidLicence() {
		this.licenceValid = true;
	}
	
	
	/*
	 * Gestion configuration en UTF-8 (load,save,reload)
	 * 
	 */

	@Override
	public void loadCustomConfig(){
		if(!getDataFolder().exists()) getDataFolder().mkdir();
		File configFile = new File(getDataFolder(), "config.yml");
		try {
			if(!configFile.exists()) {
				InputStream in = getResourceAsStream("config.yml");
				Files.copy(in, configFile.toPath());
			}
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reloadConfig() {
		try {
			File configFile = new File(getDataFolder(), "config.yml");
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 
	 * Gestion des managers interne au Module
	 * 
	 * 
	 */

	@Override
	public void registerManager(AManager manager) {
		if(manager == null) { return;}

		if(manager instanceof Setupable && ((Setupable) manager).getUseConfig()) registerSetupable((Setupable) manager);

		if(manager instanceof Informable) {
			((Informable) manager).informe();
		}

		this.managers.add(manager);
		log(manager.logEnabled());
	}

	@Override
	public void unregisterManager(AManager manager) {
		this.managers.remove(manager);
	}


	@Override
	public List<AManager> getInternalManagers() {
		return this.managers;
	}

	@Override
	public <T extends IManager> T getManager(Class<T> clazz) {
		return instance.getManager(clazz);
	}

	@Override
	public <T extends IManager> T getInternalManager(Class<T> clazz) {
		for(IManager manager : managers) {
			if(manager.getClass().equals(clazz)) return (T) manager;
		}
		return null;
	}


	/*
	 * 
	 * Method explicite pour les modules qui utilise les composents de l'api
	 * 
	 */

	@Override
	public void registerSetupable(ISetupable<IPlugin<?>> setupable) {
		instance.registerSetupable(setupable);
	}

	@Override
	public void unregisterSetupable(ISetupable<IPlugin<?>> setupable) {
		instance.unregisterSetupable(setupable);
	}

	@Override
	public Listener registerListener(Listener listener) {
		return instance.registerListener(listener);
	}

	@Override
	public void unregisterAllListeners() {
		instance.unregisterAllListeners();
	}

	@Override
	public void callEvent(Event event) {
		instance.callEvent(event);
	}

	
	/*
	 * 
	 * Gestion des logs
	 * 
	 */

	@Override
	public void log(String... args) {
		instance.log(args);
	}

	@Override
	public void logWarn(String... args) {
		instance.logWarn(args);
	}

	@Override
	public void logErr(String... args) {
		instance.logErr(args);
	}

	@Override
	public void log(Object... args) {
		instance.log(args);
	}

	@Override
	public void logWarn(Object... args) {
		instance.logWarn(args);
	}

	@Override
	public void logErr(Object... args) {
		instance.logErr(args);
	}
	
	@Override
	public long getStartMillis() {
		return 0;
	}

}
