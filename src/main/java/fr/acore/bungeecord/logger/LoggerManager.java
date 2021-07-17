package fr.acore.bungeecord.logger;


import fr.acore.bungeecord.ACoreBungeeCordAPI;
import fr.acore.bungeecord.api.logger.ILogger;
import fr.acore.bungeecord.api.logger.ILoggerManager;
import fr.acore.bungeecord.api.logger.Levels;
import fr.acore.bungeecord.api.plugin.IPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoggerManager implements ILoggerManager {

    private IPlugin<?> plugin;

    //gestion du printer
    private boolean printerActivated;

    //gestion formatage a l'affichage
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String messageFormat;

    public LoggerManager(IPlugin<?> plugin, String messageFormat){
        this.plugin = plugin;
        this.printerActivated = false;
        this.messageFormat = messageFormat;
    }

    /*

    Logger

     */

    @Override
    public ILogger getLogger() {
        return new ILogger() {
            @Override
            public void log(String... args) {
                for(String message : args)
                    System.out.println(replaceAll(message, Levels.INFO));
            }

            @Override
            public void logWarn(String... args) {
                for(String message : args)
                    System.out.println(replaceAll(message, Levels.WARN));
            }

            @Override
            public void logErr(String... args) {
                for(String message : args)
                    System.err.println(replaceAll(message, Levels.ERR));
            }

            @Override
            public void log(Object... args) {
                for(Object message : args)
                    System.out.println(replaceAll(message.toString(), Levels.INFO));
            }

            @Override
            public void logWarn(Object... args) {
                for(Object message : args)
                    System.out.println(replaceAll(message.toString(), Levels.WARN));
            }

            @Override
            public void logErr(Object... args) {
                for(Object message : args)
                    System.err.println(replaceAll(message.toString(), Levels.ERR));
            }
        };
    }

    @Override
    public void log(String... args) {
        getLogger().log(args);
    }

    @Override
    public void logWarn(String... args) {
        getLogger().logWarn(args);
    }

    @Override
    public void logErr(String... args) {
        getLogger().logErr(args);
    }

    @Override
    public void log(Object... args) {
        getLogger().log(args);
    }

    @Override
    public void logWarn(Object... args) {
        getLogger().logWarn(args);
    }

    @Override
    public void logErr(Object... args) {
        getLogger().logErr(args);
    }

    @Override
    public IPlugin<?> getPlugin() {
        return plugin;
    }

    /*

    Replacement des arguments pendant le log (%message%, %level%, %data%)

     */

    private String replaceAll(String message, Levels level) {
        message = messageFormat.replace("%message%", message);
        message = message.replace("%level%", level.getDesc());
        message = message.replace("%date%", DATE_FORMAT.format(Calendar.getInstance().getTime()));
        return message;
    }

    /*

    Getter / Setter

     */

    @Override
    public boolean isPrinterActivated() {
        return this.printerActivated;
    }
}
