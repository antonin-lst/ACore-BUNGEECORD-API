package fr.acore.bungeecord.jedis.listener;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.manager.RedisManager;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerJoinProxyPacket;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerQuitProxyPacket;
import fr.acore.bungeecord.lobby.LobbyManager;
import fr.acore.bungeecord.utils.PremiumUtils;
import io.netty.handler.proxy.ProxyConnectionEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisListener implements Listener {

    private ACoreBungeeCordAPI instance;

    private Map<ProxiedPlayer, String> targetServers;

    public RedisListener(ACoreBungeeCordAPI instance){
        this.instance = instance;
        this.targetServers = new HashMap<>();
    }

    @EventHandler
    public void onPostLoginEvent(PostLoginEvent event){
        String defaultServer = instance.getManager(LobbyManager.class).getFutureLobby().getName();
        targetServers.put(event.getPlayer(), defaultServer);
        instance.getManager(RedisManager.class).sendPacket(new PlayerJoinProxyPacket(PremiumUtils.generateOfflineId(event.getPlayer().getDisplayName()).toString(), event.getPlayer().getDisplayName(), defaultServer));
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent event){
        if(targetServers.containsKey(event.getPlayer())){
            event.setTarget(ProxyServer.getInstance().getServerInfo(targetServers.get(event.getPlayer())));
            targetServers.remove(event.getPlayer());
        }
    }

    @EventHandler
    public void onQuitProxyEvent(PlayerDisconnectEvent event){
        if(targetServers.containsKey(event.getPlayer())){
            targetServers.remove(event.getPlayer());
        }
        instance.getManager(RedisManager.class).sendPacket(new PlayerQuitProxyPacket(PremiumUtils.generateOfflineId(event.getPlayer().getDisplayName()).toString()));
    }



}
