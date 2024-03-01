package it.unipv.ingsfw.opinione360.util;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Classe che permette di deserializzare oggetti di dominio
 * @see com.google.gson.JsonDeserializer
 */
public class CustomDeserializer implements JsonDeserializer {
    /**
     * Implementazione del metodo deserialize
     * @param jsonElement l'elemento Json da deserializzare
     * @param type
     * @param jsonDeserializationContext
     * @return l'oggetto deserializzato
     * @throws JsonParseException
     */
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        String type1 = json.get("type").getAsString();
        try {
            return jsonDeserializationContext.deserialize(json, Class.forName(type1+"C" ));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
