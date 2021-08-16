package fr.acore.bungeecord.config.manager;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.config.IConfigManager;
import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.config.commands.ReloadCommand;
import fr.acore.bungeecord.config.utils.Conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager implements IConfigManager<IPlugin<?>> {

	private ACoreBungeeCordAPI plugin;
	public IPlugin<?> getPlugin() { return this.plugin;}
	
	private Conf conf;
	public Conf getConf() {	return this.conf;}
	
	private Map<IPlugin<?>, List<ISetupable<IPlugin<?>>>> configParticipator;
	
	
	public ConfigManager(ACoreBungeeCordAPI plugin) {
		this.plugin = plugin;
		configParticipator = new HashMap<>();
		addSetupable(conf = new Conf(plugin));
		plugin.getProxy().getPluginManager().registerCommand(plugin, new ReloadCommand(plugin));
	}
	
	/*
	 * 
	 * Gestion de la Map des classes de configuration
	 * 
	 */

	@Override
	public Map<IPlugin<?>, List<ISetupable<IPlugin<?>>>> getSetupables() {
		return this.configParticipator;
	}

	@Override
	public void addSetupable(ISetupable<IPlugin<?>> setupable) {
		if(setupable == null || setupable.getKey() == null) return;
		
		if(configParticipator.containsKey(setupable.getKey())) {
			configParticipator.get(setupable.getKey()).add(setupable);
		}else {
			List<ISetupable<IPlugin<?>>> setupableList = new ArrayList<>();
			setupableList.add(setupable);
			configParticipator.put(setupable.getKey(), setupableList);
		}
	}

	@Override
	public void removeSetupable(ISetupable<IPlugin<?>> setupable) {
		configParticipator.get(setupable.getKey()).remove(setupable);
	}
	
	@Override
	public void removeAllSetupable(IPlugin<?> key) {
		configParticipator.remove(key);
	}

	/*
	 * 
	 * Recharger la configuration d'un setupable ou de tout les setupable
	 * 
	 */
	
	@Override
	public void reload() {
		for(Map.Entry<IPlugin<?>, List<ISetupable<IPlugin<?>>>> entry : this.configParticipator.entrySet()){
			entry.getKey().reloadConfig();
			for(ISetupable<IPlugin<?>> setupable : entry.getValue()){
				setupable.setup(entry.getKey().getConfig());
			}
		}
	}

	@Override
	public void reload(ISetupable<IPlugin<?>> setupable) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 
	 * Gestion des logs
	 * 
	 */
	
	@Override
	public void log(String... args) {
		plugin.log(args);
	}
	
	@Override
	public void log(Object... args) {
		plugin.log(args);
	}

	@Override
	public void logWarn(String... args) {
		plugin.logWarn(args);
	}
	
	@Override
	public void logWarn(Object... args) {
		plugin.logWarn(args);
	}

	@Override
	public void logErr(String... args) {
		plugin.logErr(args);
	}

	@Override
	public void logErr(Object... args) {
		plugin.logErr(args);
	}

}
