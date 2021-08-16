package fr.acore.bungeecord.api.manager;


import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.logger.ILogger;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.version.Version;
import fr.acore.bungeecord.module.AManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;

import java.io.File;

public interface IManager extends ILogger {

	default public String logEnabled() {
		return getClass().getSimpleName() + ChatColor.YELLOW + " Enabled";
	}
	
	public IPlugin<?> getPlugin();

	
}
