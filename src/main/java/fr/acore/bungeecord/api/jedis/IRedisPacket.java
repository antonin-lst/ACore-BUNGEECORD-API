package fr.acore.bungeecord.api.jedis;

import com.google.gson.Gson;
import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.json.JsonSerializable;
import fr.acore.bungeecord.api.packet.IPacket;

public interface IRedisPacket extends JsonSerializable, IPacket {

    public static final Gson gson = new Gson();

    public void handle(ACoreBungeeCordAPI instance);

    public String getChannel();
}
