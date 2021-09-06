package fr.acore.bungeecord.lobby;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.manager.IManager;
import fr.acore.bungeecord.api.plugin.IPlugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class LobbyManager implements IManager {

    private ACoreBungeeCordAPI instance;

    private List<ServerInfo> servers;

    public LobbyManager(ACoreBungeeCordAPI instance){
        this.instance = instance;
        this.servers = new ArrayList<>();
    }


    /*


    gestion des lobbys


     */

    public void addLobby(String serverName){
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);

        if(serverInfo == null){
            logErr("Le lobby " + serverName + " n'existe pas");
            return;
        }

        this.servers.add(serverInfo);
    }

    public void removeLobby(String serverName){
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);

        if(serverInfo == null){
            logErr("Le lobby " + serverName + " n'existe pas");
            return;
        }
        servers.remove(serverInfo);
    }

    public ServerInfo getFutureLobby(){
        if(servers.isEmpty()) return null;

        ServerInfo betterHub = servers.get(0);

        for(ServerInfo hub : servers) {
            if(hub.getPlayers().size() < betterHub.getPlayers().size()) betterHub = hub;
        }

        return betterHub;
    }


    /*
     *
     * Gestion des logs
     *
     */

    @Override
    public void log(String... args) {
        instance.log(args);
    }

    @Override
    public void log(Object... args) {
        instance.log(args);
    }

    @Override
    public void logWarn(String... args) {
        instance.logWarn(args);
    }

    @Override
    public void logWarn(Object... args) {
        instance.logWarn(args);
    }

    @Override
    public void logErr(String... args) {
        instance.logErr(args);
    }

    @Override
    public void logErr(Object... args) {
        instance.logErr(args);
    }

    @Override
    public IPlugin<?> getPlugin() {
        return instance;
    }
}
