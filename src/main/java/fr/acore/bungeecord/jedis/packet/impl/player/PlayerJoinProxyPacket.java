package fr.acore.bungeecord.jedis.packet.impl.player;



import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;

import java.util.UUID;

public class PlayerJoinProxyPacket extends RedisPacket {

    private String playerUUID;
    private String playerName;
    private String serverLoginName;

    public PlayerJoinProxyPacket(){}

    public PlayerJoinProxyPacket(String playerUUID, String playerName, String serverLoginName){
        this.playerUUID = playerUUID;
        this.playerName = playerName;
        this.serverLoginName = serverLoginName;
    }

    @Override
    public void handle(ACoreBungeeCordAPI instance) {

    }


    @Override
    public int getId() {
        return 5;
    }

}
