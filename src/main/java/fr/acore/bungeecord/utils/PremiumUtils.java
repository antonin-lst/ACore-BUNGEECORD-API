package fr.acore.bungeecord.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import fr.acore.bungeecord.ACoreBungeeCordAPI;
import net.md_5.bungee.api.plugin.Plugin;

public class PremiumUtils {
	
	private static Plugin plugin = ACoreBungeeCordAPI.getInstance();
	
	public static void isPremiumName(String name, RequestCallBack callBack) {
		try {
			asyncGetRequest(new URL("https://api.mojang.com/users/profiles/minecraft/" + name), callBack);
		} catch (MalformedURLException e) {
			callBack.callBack(false, "", e, -1);
		}
	}
	
	public static void asyncGetRequest(URL url, RequestCallBack callBack) {
		plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
			
			@Override
			public void run() {
				
				StringBuilder response = new StringBuilder();
				
				try {
					
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					 
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					
					String line = "";
					while((line = reader.readLine()) != null) response.append(line);
					
					reader.close();
					callBack.callBack(true, response.toString(), null, connection.getResponseCode());
					
				}catch(Exception e) {
					callBack.callBack(false, response.toString(), e, -1);
				}
				
			}
			
		});
	}
	
	/*
    public static boolean isPremiumName(final String name, final RequestCallBack callBack) {
        try {
            final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            final StringBuilder string = new StringBuilder();
            try (final BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    string.append(line);
                }
            }
            if (!string.toString().isEmpty()) {
                return true;
            }
        }
        catch (IOException exception) {
            System.out.println("Mojang servers not responding!");
        }
        return false;
    }*/
    
    
	/*
	 * 
	 * UUID generator (ServerSide)
	 * 
	 */
	
    public static UUID generateOfflineId(String playerName) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(StandardCharsets.UTF_8));
    }
    
    
    /*
     * 
     * Post execution CallBack
     * 
     */
    
    public static interface RequestCallBack {
        
    	void callBack(boolean successful, String response, Exception exception, int responseCode);
    
    }

}
