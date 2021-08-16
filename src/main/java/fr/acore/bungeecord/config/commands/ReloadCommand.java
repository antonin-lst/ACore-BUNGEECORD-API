package fr.acore.bungeecord.config.commands;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.config.manager.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

	private ACoreBungeeCordAPI instance;
	
	public ReloadCommand(ACoreBungeeCordAPI instance) {
		super("reload", "acore.reload");
		this.instance = instance;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		instance.getInternalManager(ConfigManager.class).reload();
	}

}
