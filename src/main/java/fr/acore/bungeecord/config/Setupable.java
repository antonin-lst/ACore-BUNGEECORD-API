package fr.acore.bungeecord.config;


import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.plugin.IPlugin;
import net.md_5.bungee.api.ChatColor;

public abstract class Setupable implements ISetupable<IPlugin<?>> {

	/*
	 * 
	 * Plugin au quelle apartient la config
	 * 
	 */
	
	protected IPlugin<?> key;
	
	/*
	 * 
	 * Vrai utilisation de la configuration ou heritage obligatoire
	 * 
	 */
	
	private boolean useConfig;
	public boolean getUseConfig() { return this.useConfig;}
	
	public Setupable(IPlugin<?> key, boolean useConfig) {
		this.key = key;
		this.useConfig = useConfig;
		
		//Tentative de chargement de la configuration
		if(useConfig) {
			try {
				setup(key.getConfig());
				key.log("La configuration de la classe " + ChatColor.YELLOW + getClass().getSimpleName() + ChatColor.GREEN + " est charg�e avec succes");
			}catch(Exception ex) {
				key.logErr(ChatColor.GREEN + "Erreur de chargement de la configuration de la classe " + ChatColor.YELLOW + getClass().getSimpleName());
				if(ex.getMessage() != null && !ex.getMessage().isEmpty()) {
					key.logErr(ChatColor.YELLOW + "Error Message : " + ChatColor.RED + ex.getMessage());
				}else {
					ex.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 
	 * getter du plugin d'apartenance
	 * 
	 */
	
	@Override
	public IPlugin<?> getKey() {
		return this.key;
	}

}
