package fr.acore.bungeecord.module.manager;



import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.plugin.module.IModuleManager;
import fr.acore.bungeecord.module.AModule;

import java.util.ArrayList;
import java.util.List;

public class AModuleManager implements IModuleManager {

	private ACoreBungeeCordAPI plugin;
	public IPlugin<?> getPlugin(){ return this.plugin;}
	
	private List<AModule> modules;
	
	public AModuleManager(ACoreBungeeCordAPI plugin) {
		this.plugin = plugin;
		this.modules = new ArrayList<>();
	}
	
	/*
	 * 
	 * Gestion de la liste des module disponible
	 * 
	 */
	
	@Override
	public List<AModule> getModules() {
		return this.modules;
	}

	@Override
	public void addModule(AModule module) {
		this.modules.add(module);
	}

	@Override
	public void removeModule(AModule module) {
		this.modules.remove(module);
	}
	
	/*
	 * 
	 * Gestion de la licence d'un module
	 * 
	 */

	@Override
	public void setModuleLicenceChecked(String moduleName, boolean valid) {
		for(AModule module : modules) {
			if(module.getPluginName().equals(moduleName)) {
				
				if(valid) module.setValidLicence();
				
				module.setLicenceChecked();
			}
		}
	}
	
	/*
	 * 
	 * Permet de r�cuperer un manager par ca class dans la list des modules existant
	 * voir HookSysteme
	 * 
	 */

	@Override
	public <T extends IManager> T getInModulesManager(Class<T> clazz) {
		for(AModule module : modules) {
			if(module.getInternalManager(clazz) != null) return (T) module.getInternalManager(clazz);
		}
		return null;
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
