package fr.acore.bungeecord.jedis.packet.impl.player;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;

public class PlayerQuitProxyPacket extends RedisPacket {

    private String playerUUID;

    public PlayerQuitProxyPacket(){}

    public PlayerQuitProxyPacket(String playerUUID){
        this.playerUUID = playerUUID;
    }

    @Override
    public void handle(ACoreBungeeCordAPI instance) {

    }

    @Override
    public int getId() {
        return 6;
    }
}
