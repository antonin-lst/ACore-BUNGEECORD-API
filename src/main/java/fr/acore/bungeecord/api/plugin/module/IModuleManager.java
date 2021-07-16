package fr.acore.bungeecord.api.plugin.module;


import fr.acore.bungeecord.api.manager.IManager;

import java.util.List;

public interface IModuleManager extends IManager {
	
	public List<IModule> getModules();
	public void addModule(IModule module);
	public void removeModule(IModule module);
	
	public void setModuleLicenceChecked(String moduleName, boolean valid);
	
	public <T extends IManager> T getInModulesManager(Class<T> clazz);

}
