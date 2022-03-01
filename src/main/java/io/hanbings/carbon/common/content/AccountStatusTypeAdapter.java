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

public class AccountStatusTypeAdapter implements JsonSerializer<AccountStatusType>, JsonDeserializer<AccountStatusType> {

    @Override
    public JsonElement serialize(AccountStatusType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString().toLowerCase(Locale.ROOT));
    }

    @Override
    public AccountStatusType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return AccountStatusType.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
    }
}
