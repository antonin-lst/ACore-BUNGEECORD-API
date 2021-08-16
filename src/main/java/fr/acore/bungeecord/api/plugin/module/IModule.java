package fr.acore.bungeecord.api.plugin.module;


import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.version.Version;
import fr.acore.bungeecord.module.AManager;

public interface IModule {

	public Version getApiVersion() throws Version.ParseVersionException;
	
	public boolean isValidLicence();
	public void setValidLicence();
	
	public boolean isLicenceChecked();
	public void setLicenceChecked();
	
}
