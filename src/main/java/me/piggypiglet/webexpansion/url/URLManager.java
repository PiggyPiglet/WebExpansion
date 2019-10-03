package me.piggypiglet.webexpansion.url;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.managers.implementations.SearchableManager;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.webexpansion.url.objects.URL;

import java.util.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class URLManager extends SearchableManager<URL> {
    private static final URL DEF = new URL("null", "null", new ArrayList<>());

    @Inject @Config private FileConfiguration config;

    private final Map<String, URL> urls = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    protected void populate() {
        config.getConfigList("urls").forEach(c -> {
            final URL url = new URL(c.getString("identifier"), c.getString("url"), (List<Map<String, Object>>) c.getList("headers"));
            urls.put(url.getName(), url);
            items.add(url);
        });
    }

    @Override
    protected KeyTypeInfo configure(KeyTypeInfo.Builder builder) {
        return builder
                .clazz(String.class)
                    .map(urls, DEF)
                .build();
    }

    @Override
    protected void insert(URL url) {
        urls.put(url.getName(), url);
    }

    @Override
    protected void delete(URL url) {
        urls.remove(url.getName());
    }

    @Override
    protected Collection<URL> retrieveAll() {
        return items;
    }
}
