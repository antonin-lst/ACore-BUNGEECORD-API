package fr.acore.bungeecord.api.jedis;

import com.google.gson.JsonObject;
import fr.acore.bungeecord.api.packet.IPacketManager;

public interface IRedisPacketManager extends IPacketManager<IRedisPacket, JsonObject> {


    public void disable();

    public IJedisPublisher getPublisher();

}
