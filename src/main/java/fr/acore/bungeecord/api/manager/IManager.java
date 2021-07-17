package fr.acore.bungeecord.api.manager;


import fr.acore.bungeecord.api.logger.ILogger;
import fr.acore.bungeecord.api.plugin.IPlugin;
import net.md_5.bungee.api.ChatColor;

public interface IManager extends ILogger {

	default public String logEnabled() {
		return getClass().getSimpleName() + ChatColor.YELLOW + " Enabled";
	}
	
	public IPlugin<?> getPlugin();
	
}
