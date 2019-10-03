package me.piggypiglet.webexpansion.url.objects;

import me.piggypiglet.framework.utils.SearchUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class URL implements SearchUtils.Searchable {
    private final String identifier;
    private final String url;
    private final List<Header> headers = new ArrayList<>();

    public URL(String identifier, String url, List<Map<String, Object>> headers) {
        this.identifier = identifier;
        this.url = url;
        headers.forEach(m -> this.headers.add(new Header((String) m.get("key"), (String) m.get("value"))));
    }

    @Override
    public String getName() {
        return identifier;
    }

    public String getUrl() {
        return url;
    }

    public List<Header> getHeaders() {
        return headers;
    }
}
