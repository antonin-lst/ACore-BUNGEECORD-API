package fr.acore.bungeecord.api.jedis;

public interface IJedisPublisher extends Runnable {

    public void write(IRedisPacket packetToJson);

}
