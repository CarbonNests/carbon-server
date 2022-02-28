package io.hanbings.carbon.common.content;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;

import java.lang.reflect.Type;
import java.util.Locale;

public class AuthPlatformTypeAdapter implements JsonSerializer<AuthPlatformType>, JsonDeserializer<AuthPlatformType> {
    @Override
    public JsonElement serialize(AuthPlatformType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString().toLowerCase(Locale.ROOT));
    }

    @Override
    public AuthPlatformType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return AuthPlatformType.lookup(json.getAsString());
    }
}
