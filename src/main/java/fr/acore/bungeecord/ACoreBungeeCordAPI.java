package fr.acore.bungeecord;

import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.version.Version;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ACoreBungeeCordAPI extends Plugin implements IPlugin<IManager> {

    /*

    Instance ACoreBungeeCordAPI

     */

    private static ACoreBungeeCordAPI instance;
    public static ACoreBungeeCordAPI getInstance(){ return instance;}

    /*
     * Plugin information (ServerName, PluginName, PluginVersion)
     *
     */

    public String getServerName() { return new File("").getAbsoluteFile().getName();}
    public String getPluginName() { return getClass().getSimpleName();}
    public Version getPluginVersion() throws Version.ParseVersionException { return Version.fromString(getDescription().getVersion());}

    /*
     *
     * FileConfiguraiton (yaml)
     *
     */
    private File configFile = new File(getDataFolder() + File.separator + "config.yml");
    public File getConfigFile() { return this.configFile;}
    private Configuration config;
    public Configuration getConfig() { return this.config;}

    /*
     * IManager list par module
     *
     */
    private List<IManager> managers;

    	/*

	Gestion de la précence du ACoreMain

	 */

    private static boolean acoreMainPresence;
    public static boolean getAcoreMainPresence(){ return acoreMainPresence;}
    public void setAcoreMainPresence(){ acoreMainPresence = true;}


    /*
     * gestion du temps de lancement du ACore
     *
     */

    protected Long startMillis;
    @Override
    public long getStartMillis() {return this.startMillis;}


    /*
     *
     * Methods appel�e quand le plugin change d'etat herit�e de JavaPlugin
     *
     *
     */


    @Override
    public void onEnable() {
        //instance de l'api
        instance = this;
        //timestamp du demarage
        startMillis = System.currentTimeMillis();
        //initialisation des managers
        managers = new ArrayList<>();
        //registration du Logger
        registerManager(new LoggerManager(this));


    }

    @Override
    public void log(String... args) {

    }

    @Override
    public void logWarn(String... args) {

    }

    @Override
    public void logErr(String... args) {

    }

    @Override
    public void log(Object... args) {

    }

    @Override
    public void logWarn(Object... args) {

    }

    @Override
    public void logErr(Object... args) {

    }

    @Override
    public List<IManager> getInternalManagers() {
        return null;
    }

    @Override
    public <T extends IManager> T getInternalManager(Class<T> clazz) {
        return null;
    }

    @Override
    public <T extends IManager> T getManager(Class<T> clazz) {
        return null;
    }

    @Override
    public void registerManager(IManager manager) {

    }

    @Override
    public void unregisterManager(IManager manager) {

    }

    @Override
    public void loadCustomConfig() {

    }

    @Override
    public void reloadConfig() {

    }

    @Override
    public void registerSetupable(ISetupable<IPlugin<?>> setupable) {

    }

    @Override
    public void unregisterSetupable(ISetupable<IPlugin<?>> setupable) {

    }

    @Override
    public Listener registerListener(Listener listener) {
        return null;
    }

    @Override
    public void unregisterAllListeners() {

    }

    @Override
    public void callEvent(Event event) {

    }
}
