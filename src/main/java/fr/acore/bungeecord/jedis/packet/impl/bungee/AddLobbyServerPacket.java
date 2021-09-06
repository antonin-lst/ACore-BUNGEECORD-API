package fr.acore.bungeecord.jedis.packet.impl.bungee;


import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;
import fr.acore.bungeecord.lobby.LobbyManager;

public class AddLobbyServerPacket extends RedisPacket {

    private String serverName;

    public AddLobbyServerPacket(){}

    public AddLobbyServerPacket(String serverName){this.serverName = serverName;}



    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        instance.getManager(LobbyManager.class).addLobby(serverName);
    }

    @Override
    public int getId() {
        return 11;
    }
}
