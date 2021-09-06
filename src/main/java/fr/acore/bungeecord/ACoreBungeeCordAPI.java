package fr.acore.bungeecord;

import fr.acore.bungeecord.api.config.ISetupable;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.manager.Informable;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.storage.database.DBUser;
import fr.acore.bungeecord.api.storage.database.IDatabase;
import fr.acore.bungeecord.api.storage.database.driver.DatabaseDriver;
import fr.acore.bungeecord.api.storage.exception.DBNotFoundException;
import fr.acore.bungeecord.api.storage.exception.schema.SchemaNotFounException;
import fr.acore.bungeecord.api.storage.factory.IDataFactory;
import fr.acore.bungeecord.api.version.Version;
import fr.acore.bungeecord.config.Setupable;
import fr.acore.bungeecord.config.manager.ConfigManager;
import fr.acore.bungeecord.config.utils.Conf;
import fr.acore.bungeecord.cryptographie.CryptoManager;
import fr.acore.bungeecord.cryptographie.CryptoType;
import fr.acore.bungeecord.jedis.manager.RedisManager;
import fr.acore.bungeecord.jedis.packet.impl.bungee.AddLobbyServerPacket;
import fr.acore.bungeecord.jedis.packet.impl.bungee.RemoveLobbyServerPacket;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerChangeServerPacket;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerJoinProxyPacket;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerQuitProxyPacket;
import fr.acore.bungeecord.jedis.packet.impl.player.SendPlayerMessagePacket;
import fr.acore.bungeecord.jedis.packet.impl.server.InitServerPacket;
import fr.acore.bungeecord.jedis.packet.impl.server.StopServerPacket;
import fr.acore.bungeecord.jedis.packet.impl.server.UpdateServerPacket;
import fr.acore.bungeecord.lobby.LobbyManager;
import fr.acore.bungeecord.logger.LoggerManager;
import fr.acore.bungeecord.module.manager.AModuleManager;
import fr.acore.bungeecord.storage.StorageManager;
import fr.acore.bungeecord.storage.database.MySqlDatabase;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
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
        registerManager(new LoggerManager(this, "[%date% ACoreBungeeCord] %message%"));

        loadCustomConfig();
        registerManager(new ConfigManager(this));
        getInternalManager(ConfigManager.class).addSetupable(new Conf(this));

        try {
            //création du storage en fonction de la configuration (config.yml) !! support actuelle MYSQL !!
            IDatabase<?> database = Conf.getStorageType().equals(DatabaseDriver.MYSQL) ? new MySqlDatabase("maindb", new DBUser(Conf.getUser(), Conf.getPass()), Conf.getHost()) : null;
            StorageManager storageM = new StorageManager(this, database);
            storageM.load();

            //registration du storage
            registerManager(storageM);

            //registration de la base de donnée par default
            storageM.setDefaultDatabase("maindb");

            //registration du schema en configuration
            storageM.getDefaultDatabase().addSchema(Conf.getDatabase());
            storageM.getDefaultDatabase().setDefaultSchema(Conf.getDatabase());
        } catch (DBNotFoundException | SchemaNotFounException e) {e.printStackTrace();}

        //registration du syteme de cryptographie
        registerManager(new CryptoManager(this, CryptoType.BCRYPT));

        //registration du systeme de lobby
        registerManager(new LobbyManager(this));
        //registration du systeme de packet Redis
        RedisManager redisManager;
        registerManager(redisManager = new RedisManager(this));
        //registration des packets //id 1 est pour le packet de test
        //packets serveurs
        redisManager.getPacketFactory().addPacket(2, InitServerPacket.class);
        redisManager.syncCheckACoreMainPresence();
        redisManager.getPacketFactory().addPacket(3, StopServerPacket.class);
        redisManager.getPacketFactory().addPacket(4, UpdateServerPacket.class);
        redisManager.getPacketFactory().addPacket(5, PlayerJoinProxyPacket.class);
        redisManager.getPacketFactory().addPacket(6, PlayerQuitProxyPacket.class);
        redisManager.getPacketFactory().addPacket(7, PlayerChangeServerPacket.class);
        redisManager.getPacketFactory().addPacket(8, SendPlayerMessagePacket.class);

      //  redisManager.getPacketFactory().addPacket(8, AddPlayerToServerQueuePacket.class);
     //   redisManager.getPacketFactory().addPacket(9, RemovePlayerToServerQueuePacket.class);

        redisManager.getPacketFactory().addPacket(11, AddLobbyServerPacket.class);
        redisManager.getPacketFactory().addPacket(12, RemoveLobbyServerPacket.class);


        //registration du systeme de module
        registerManager(new AModuleManager(this));

        long enablingTime = System.currentTimeMillis() - startMillis;

        log("Enabled took : " + enablingTime + "ms");
    }

    @Override
    public void onDisable() {
        /*
         * Save and disable Storage
         *
         */
        getInternalManager(StorageManager.class).save();

    }

    /*
     *
     * Gestion du fichier de configuration
     *
     */

    @Override
    public void loadCustomConfig(){
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            if(!configFile.exists()) {
                InputStream in = getResourceAsStream("config.yml");
                Files.copy(in, configFile.toPath());
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reloadConfig() {
        try {
            File configFile = new File(getDataFolder(), "config.yml");
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    /*
     *
     * Gestion des managers internes au ACoreSpigotAPI
     *
     *
     */

    @Override
    public void registerManager(IManager manager) {
        if(manager == null) { return;}

        if(manager instanceof Setupable && ((Setupable) manager).getUseConfig()) registerSetupable((Setupable) manager);

        if(manager instanceof Informable) {
            ((Informable) manager).informe();
        }

        this.managers.add(manager);
        log(manager.logEnabled());
    }

    @Override
    public void unregisterManager(IManager manager) {
        this.managers.remove(manager);
    }

    @Override
    public List<IManager> getInternalManagers() {
        return this.managers;
    }

    @Override
    public <T extends IManager> T getInternalManager(Class<T> clazz) {
        for(IManager manager : managers) {
            if(manager.getClass().equals(clazz)) return (T) manager;
        }
        return null;
    }


    /*
     *
     * Gestion des Managers interne et externe
     *
     */

    @Override
    public <T extends IManager> T getManager(Class<T> clazz) {
        IManager manager = getInternalManager(clazz);
        if(manager == null) {
            log("Trying to found manager on module");
            //manager = getInternalManager(AModuleManager.class).getInModulesManager(clazz);
        }
        return (T) manager;
    }


    @Override
    public void registerSetupable(ISetupable<IPlugin<?>> setupable) {

    }

    @Override
    public void unregisterSetupable(ISetupable<IPlugin<?>> setupable) {

    }

    @Override
    public Listener registerListener(Listener listener) {
        getProxy().getPluginManager().registerListener(this, listener);
        return listener;
    }

    @Override
    public void unregisterAllListeners() {

    }

    @Override
    public void callEvent(Event event) {

    }

    @Override
    public void registerDataFactory(IDataFactory<?, ?> factory) {
        getInternalManager(StorageManager.class).addDataFactory(factory);
    }

    /*
     *
     * Gestion des logs
     *
     */

    @Override
    public void log(String... args) {
        getInternalManager(LoggerManager.class).log(args);
    }

    @Override
    public void log(Object... args) {
        getInternalManager(LoggerManager.class).log(args);
    }


    @Override
    public void logWarn(String... args) {
        getInternalManager(LoggerManager.class).logWarn(args);
    }

    @Override
    public void logWarn(Object... args) {
        getInternalManager(LoggerManager.class).logWarn(args);
    }

    @Override
    public void logErr(String... args) {
        getInternalManager(LoggerManager.class).logErr(args);
    }


    @Override
    public void logErr(Object... args) {
        getInternalManager(LoggerManager.class).logErr(args);
    }

}
