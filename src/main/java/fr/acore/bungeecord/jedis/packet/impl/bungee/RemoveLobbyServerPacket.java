package fr.acore.bungeecord.jedis.packet.impl.bungee;


import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.channel.JedisChannelHandling;
import fr.acore.bungeecord.jedis.packet.RedisPacket;
import fr.acore.bungeecord.lobby.LobbyManager;

public class RemoveLobbyServerPacket extends RedisPacket {

    private String serverName;

    public RemoveLobbyServerPacket(){}

    public RemoveLobbyServerPacket(String serverName){
        this.serverName = serverName;
    }

    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        instance.getManager(LobbyManager.class).removeLobby(serverName);
    }

    @Override
    public int getId() {
        return 12;
    }
}
