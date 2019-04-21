package com.ensighten.google.gson;

import com.ensighten.google.gson.internal.Streams;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
    public final JsonElement parse(String json) throws JsonSyntaxException {
        return parse(new StringReader(json));
    }

    public final JsonElement parse(Reader json) throws JsonIOException, JsonSyntaxException {
        try {
            JsonReader jsonReader = new JsonReader(json);
            JsonElement parse = parse(jsonReader);
            if (parse.isJsonNull() || jsonReader.peek() == JsonToken.END_DOCUMENT) {
                return parse;
            }
            throw new JsonSyntaxException("Did not consume the entire document.");
        } catch (MalformedJsonException e) {
            throw new JsonSyntaxException(e);
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        } catch (NumberFormatException e22) {
            throw new JsonSyntaxException(e22);
        }
    }

    public final JsonElement parse(JsonReader json) throws JsonIOException, JsonSyntaxException {
        boolean isLenient = json.isLenient();
        json.setLenient(true);
        try {
            JsonElement parse = Streams.parse(json);
            json.setLenient(isLenient);
            return parse;
        } catch (StackOverflowError e) {
            throw new JsonParseException("Failed parsing JSON source: " + json + " to Json", e);
        } catch (OutOfMemoryError e2) {
            throw new JsonParseException("Failed parsing JSON source: " + json + " to Json", e2);
        } catch (Throwable th) {
            json.setLenient(isLenient);
        }
    }
}
