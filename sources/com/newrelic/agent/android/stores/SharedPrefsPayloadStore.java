package com.newrelic.agent.android.stores;

import android.content.Context;
import com.newrelic.agent.android.payload.Payload;
import com.newrelic.agent.android.payload.PayloadStore;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class SharedPrefsPayloadStore extends SharedPrefsStore implements PayloadStore<Payload> {
    public static final String STORE_FILE = "NRPayloadStore";

    public SharedPrefsPayloadStore(Context context) {
        this(context, STORE_FILE);
    }

    public SharedPrefsPayloadStore(Context context, String storeFilename) {
        super(context, storeFilename);
    }

    public boolean store(Payload payload) {
        return super.store(payload.getUuid(), toStringSet(payload));
    }

    public List<Payload> fetchAll() {
        List<Payload> payloads = new ArrayList();
        for (Object object : super.fetchAll()) {
            if (object instanceof HashSet) {
                try {
                    Iterator<String> iter = ((HashSet) object).iterator();
                    Payload payload = (Payload) new Gson().fromJson((String) iter.next(), Payload.class);
                    payload.putBytes(decodePayload((String) iter.next()));
                    payloads.add(payload);
                } catch (JsonSyntaxException e) {
                }
            }
        }
        return payloads;
    }

    public void delete(Payload payload) {
        super.delete(payload.getUuid());
    }

    /* Access modifiers changed, original: protected */
    public String encodePayload(Payload payload) {
        return encodeBytes(payload.getBytes());
    }

    /* Access modifiers changed, original: protected */
    public byte[] decodePayload(String encodedString) {
        return decodeStringToBytes(encodedString);
    }

    /* Access modifiers changed, original: protected */
    public String decodePayloadToString(byte[] decodedString) {
        return decodeBytesToString(decodedString);
    }

    private LinkedHashSet<String> toStringSet(Payload payload) {
        LinkedHashSet<String> stringSet = new LinkedHashSet(2);
        stringSet.add(payload.asJsonMeta());
        stringSet.add(encodePayload(payload));
        return stringSet;
    }
}
