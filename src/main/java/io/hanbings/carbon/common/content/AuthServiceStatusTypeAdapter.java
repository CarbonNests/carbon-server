package io.hanbings.carbon.common.content;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Locale;

public class AuthServiceStatusTypeAdapter implements JsonSerializer<AuthServiceStatusType>, JsonDeserializer<AuthServiceStatusType> {
    @Override
    public JsonElement serialize(AuthServiceStatusType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString().toLowerCase(Locale.ROOT));
    }

    @Override
    public AuthServiceStatusType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return AuthServiceStatusType.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
    }
}
