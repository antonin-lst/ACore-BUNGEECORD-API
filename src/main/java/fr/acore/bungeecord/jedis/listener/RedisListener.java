package fr.acore.bungeecord.jedis.listener;

import fr.acore.bungeecord.jedis.manager.RedisManager;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerJoinProxyPacket;
import fr.acore.bungeecord.jedis.packet.impl.player.PlayerQuitProxyPacket;
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

    private RedisManager manager;

    private Map<ProxiedPlayer, String> targetServers;

    public RedisListener(RedisManager manager){
        this.manager = manager;
        this.targetServers = new HashMap<>();
    }

    @EventHandler
    public void onPostLoginEvent(PostLoginEvent event){
        String defaultServer = "Lobby-01";
        targetServers.put(event.getPlayer(), defaultServer);
        manager.sendPacket(new PlayerJoinProxyPacket(event.getPlayer().getUniqueId().toString(), event.getPlayer().getDisplayName(), defaultServer));
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent event){
        event.setTarget(ProxyServer.getInstance().getServerInfo(targetServers.get(event.getPlayer())));
        targetServers.remove(event.getPlayer());
    }

    @EventHandler
    public void onQuitProxyEvent(PlayerDisconnectEvent event){
        if(targetServers.containsKey(event.getPlayer())){
            targetServers.remove(event.getPlayer());
        }
        manager.sendPacket(new PlayerQuitProxyPacket(event.getPlayer().getUniqueId().toString()));
    }



}
