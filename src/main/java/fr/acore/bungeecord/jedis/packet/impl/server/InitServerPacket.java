package fr.acore.bungeecord.jedis.packet.impl.server;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;

public class InitServerPacket extends RedisPacket {

    private String serverName;

    public InitServerPacket(){}

    public InitServerPacket(String serverName){
        this.serverName = serverName;
    }

    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        instance.setAcoreMainPresence();
    }

    @Override
    public int getId() {
        return 2;
    }
}
