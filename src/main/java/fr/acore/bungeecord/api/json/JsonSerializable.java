package fr.acore.bungeecord.api.json;

import com.google.gson.JsonObject;

public interface JsonSerializable {

    public String toJson();

    public void fromJson(JsonObject json);
}
