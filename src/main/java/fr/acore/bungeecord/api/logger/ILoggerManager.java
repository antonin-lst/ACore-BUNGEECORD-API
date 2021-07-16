package fr.acore.bungeecord.api.logger;


import fr.acore.bungeecord.api.manager.IManager;

public interface ILoggerManager extends IManager, LoggerPrintter{

    public ILogger getLogger();
    public boolean isPrinterActivated();

}
