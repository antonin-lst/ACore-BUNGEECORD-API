package fr.acore.bungeecord.api.plugin.module;


import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.module.AModule;

import java.util.List;

public interface IModuleManager extends IManager {
	
	public List<AModule> getModules();
	public void addModule(AModule module);
	public void removeModule(AModule module);
	
	public void setModuleLicenceChecked(String moduleName, boolean valid);
	
	public <T extends IManager> T getInModulesManager(Class<T> clazz);

}
