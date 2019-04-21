package com.google.api.client.util;

import java.util.ArrayList;

public final class Lists {
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }

    private Lists() {
    }
}
