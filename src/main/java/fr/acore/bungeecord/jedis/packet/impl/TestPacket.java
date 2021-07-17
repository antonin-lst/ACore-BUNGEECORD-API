package fr.acore.bungeecord.jedis.packet.impl;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;

public class TestPacket extends RedisPacket {

    private String test;

    private int test2;

    public TestPacket(String channel){
        super(channel);
        this.test = ACoreBungeeCordAPI.getInstance().getServerName();
        this.test2 = 188;
    }

    @Override
        public void handle(ACoreBungeeCordAPI instance) {
        System.out.println("handle TestPacket");
    }


    @Override
    public int getId() {
        return 1;
    }
}
