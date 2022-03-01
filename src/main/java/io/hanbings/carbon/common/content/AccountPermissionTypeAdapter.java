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

public class AccountPermissionTypeAdapter implements JsonSerializer<AccountPermissionType>, JsonDeserializer<AccountPermissionType> {
    @Override
    public JsonElement serialize(AccountPermissionType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString().toLowerCase(Locale.ROOT));
    }

    @Override
    public AccountPermissionType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return AccountPermissionType.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
    }
}
