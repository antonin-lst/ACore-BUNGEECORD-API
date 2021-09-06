package fr.acore.bungeecord.jedis.packet.impl.player;


import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.jedis.packet.RedisPacket;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class SendPlayerMessagePacket extends RedisPacket {

    private String playerName;
    private String message;

    public SendPlayerMessagePacket(){}

    public SendPlayerMessagePacket(String playerName, String message){
        this.playerName = playerName;
        this.message = message;
    }


    @Override
    public void handle(ACoreBungeeCordAPI instance) {
        ProxiedPlayer player = instance.getProxy().getPlayer(playerName);
        if(player != null){
            player.sendMessage(ChatMessageType.CHAT, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }else{
            System.out.println("Le joueur : " + playerName + " n'existe pas");
        }
    }

    @Override
    public int getId() {
        return 8;
    }
}
