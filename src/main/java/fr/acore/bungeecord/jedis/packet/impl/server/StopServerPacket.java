package fr.acore.bungeecord.jedis.packet.impl.server;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;


public class StopServerPacket extends RedisPacket {

    public StopServerPacket(){}

    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        instance.getProxy().stop();
    }

    @Override
    public int getId() {
        return 3;
    }
}
