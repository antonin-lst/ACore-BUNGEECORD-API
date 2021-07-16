package fr.acore.bungeecord.api.config;

import fr.acore.bungeecord.api.manager.IManager;

import java.util.List;
import java.util.Map;

public interface IConfigManager<T> extends IManager {
	
	public Map<T, List<ISetupable<T>>> getSetupables();
	public void addSetupable(ISetupable<T> setupable);
	public void removeSetupable(ISetupable<T> setupable);
	public void removeAllSetupable(T key);
	
	public void reload();
	public void reload(ISetupable<T> setupable);

}
