package fr.acore.bungeecord.jedis.packet.impl.player;


import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.manager.RedisManager;
import fr.acore.bungeecord.jedis.packet.RedisPacket;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class PlayerChangeServerPacket extends RedisPacket {

    private String playerName;
    private String newServerName;

    public PlayerChangeServerPacket(){}

    public PlayerChangeServerPacket(String playerName, String newServerName){
        this.playerName = playerName;
        this.newServerName = newServerName;
    }

    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        if(changePlayerServer(instance.getProxy().getPlayer(playerName), instance.getProxy().getServerInfo(newServerName))){
            instance.getManager(RedisManager.class).sendPacket(new PlayerChangeServerPacket(playerName, newServerName));
        }else{
            instance.logErr("Une erreur c'est produite pendant le changement de serveur du joueur " + playerName + " sur le serveur " + newServerName);
        }
    }

    private boolean changePlayerServer(ProxiedPlayer player, ServerInfo serverInfo) {
        if(player == null) {
            System.out.println("player null");
            return false;
        }

        if(serverInfo == null) {
            System.out.println("server null");
            return false;
        }
        player.connect(serverInfo);
        System.out.println("Teleportation du joueur " + player.getDisplayName() + " sur le server " + serverInfo.getName());
        return true;
    }

    @Override
    public int getId() {
        return 7;
    }
}
