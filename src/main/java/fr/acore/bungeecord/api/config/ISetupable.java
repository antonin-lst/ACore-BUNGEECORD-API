package fr.acore.bungeecord.api.config;


import fr.acore.bungeecord.api.config.color.IColorHelper;
import org.bukkit.configuration.file.FileConfiguration;

public interface ISetupable<T> extends IColorHelper, IStringHelper{
	
	public T getKey();
	public void setup(FileConfiguration config);
	

}
