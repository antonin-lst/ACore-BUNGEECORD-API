package fr.acore.bungeecord.api.config;


import fr.acore.bungeecord.api.config.color.IColorHelper;
import net.md_5.bungee.config.Configuration;

public interface ISetupable<T> extends IColorHelper, IStringHelper{
	
	public T getKey();
	public void setup(Configuration config);
	

}
