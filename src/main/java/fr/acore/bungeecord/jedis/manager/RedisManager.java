package fr.acore.bungeecord.jedis.manager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.jedis.IJedisPublisher;
import fr.acore.bungeecord.api.jedis.IRedisPacket;
import fr.acore.bungeecord.api.jedis.IRedisPacketManager;
import fr.acore.bungeecord.api.packet.IPacketFactory;
import fr.acore.bungeecord.api.plugin.IPlugin;
import fr.acore.bungeecord.api.time.timer.ITimer;
import fr.acore.bungeecord.config.utils.Conf;
import fr.acore.bungeecord.jedis.listener.RedisListener;
import fr.acore.bungeecord.jedis.packet.RedisPacket;
import fr.acore.bungeecord.jedis.packet.factory.RedisPacketFactory;
import fr.acore.bungeecord.jedis.packet.impl.server.InitServerPacket;
import fr.acore.bungeecord.jedis.publisher.JedisPublisher;
import fr.acore.bungeecord.utils.TimerBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisManager extends JedisPubSub implements IRedisPacketManager {

    private ACoreBungeeCordAPI instance;

    private Jedis jedis;

    private RedisPacketFactory packetFactory;
    private JedisPublisher jedisPublisher;

    public RedisManager(ACoreBungeeCordAPI instance){
        this.instance = instance;
        this.jedis = new Jedis(Conf.getJedisHost(), Conf.getJedisPort());
        this.packetFactory = new RedisPacketFactory();
        this.jedisPublisher = new JedisPublisher(instance);
        instance.registerListener(new RedisListener(instance));

        if(!Conf.getJedisPassword().isEmpty()){
            jedis.auth(Conf.getJedisPassword());
        }
        new Thread(() -> {try {
            jedis.subscribe(this, instance.getServerName());
            instance.log("subscribe to " + instance.getServerName());
        }catch(Exception e) {
            instance.logErr("Redis is not enabled stoping");
            instance.getProxy().stop();
        }}).start();

        new Thread(jedisPublisher).start();
        //jedisPublisher.write(new TestPacket(JedisChannelHandling.ACORE_MAIN.getChannel()));
    }


    @Override
    public void disable() {
        if(isSubscribed()) unsubscribe();
        jedis.close();
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
        handlePacket(new JsonParser().parse(message).getAsJsonObject());
        //super.onMessage(channel, message);
    }

    @Override
    public void sendPacket(IRedisPacket packet) {
        String packetToJson = packet.toJson();
        System.out.println(packetToJson.toString());
        jedisPublisher.write((RedisPacket) packet);
    }



    @Override
    public void handlePacket(JsonObject jsonPacket) {
        int id = jsonPacket.get("id").getAsInt();
        try {
            IRedisPacket packet = getPacketFactory().getPacketClazz(id).newInstance();
            packet.fromJson(jsonPacket.get("datas").getAsJsonObject());
            packet.handle(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void syncCheckACoreMainPresence() {
        log("start check ACoreMain Presence");
        sendPacket(new InitServerPacket(instance.getServerName()));
        ITimer timer = new TimerBuilder(500);

        while(!timer.isFinish() && !ACoreBungeeCordAPI.getAcoreMainPresence()){
            //System.out.println("waiting");
        }

        if(timer.isFinish() && !ACoreBungeeCordAPI.getAcoreMainPresence()){
            log("Disable ACoreMain indisponible");
        }else {
            log("Connection au ACoreMain réussit");
        }
    }

    @Override
    public IPacketFactory<IRedisPacket> getPacketFactory() {
        return packetFactory;
    }

    @Override
    public IJedisPublisher getPublisher() {
        return jedisPublisher;
    }


    /*
     *
     * Gestion des logs
     *
     */

    @Override
    public void log(String... args) {
        instance.log(args);
    }

    @Override
    public void log(Object... args) {
        instance.log(args);
    }

    @Override
    public void logWarn(String... args) {
        instance.logWarn(args);
    }

    @Override
    public void logWarn(Object... args) {
        instance.logWarn(args);
    }

    @Override
    public void logErr(String... args) {
        instance.logErr(args);
    }

    @Override
    public void logErr(Object... args) {
        instance.logErr(args);
    }

    @Override
    public IPlugin<?> getPlugin() {
        return instance;
    }


}
