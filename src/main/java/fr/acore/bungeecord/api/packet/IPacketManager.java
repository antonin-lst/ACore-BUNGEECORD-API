package fr.acore.bungeecord.api.packet;

import fr.acore.bungeecord.api.manager.IManager;

public interface IPacketManager<T extends IPacket, U> extends IManager {

    /*

    Base d'un manager de packet

     */

    //envoie un packet
    public abstract void sendPacket(T packet);

    //recoie le packet parsé
    public abstract void handlePacket(U parsedPacket);

    //Factory
    public IPacketFactory<T> getPacketFactory();


}
