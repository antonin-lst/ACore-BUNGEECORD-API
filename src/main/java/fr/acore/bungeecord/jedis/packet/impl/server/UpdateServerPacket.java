package fr.acore.bungeecord.jedis.packet.impl.server;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.manager.RedisManager;
import fr.acore.bungeecord.jedis.packet.RedisPacket;


public class UpdateServerPacket extends RedisPacket {

    private String serverName;

    public UpdateServerPacket(){}

    public UpdateServerPacket(String serverName){
        this.serverName = serverName;
    }

    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        instance.getInternalManager(RedisManager.class).sendPacket(new UpdateServerPacket(instance.getServerName()));
    }

    @Override
    public int getId() {
        return 4;
    }
}
