package me.piggypiglet.webexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.bootstrap.FrameworkBootstrap;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.webexpansion.config.ConfigUtil;
import me.piggypiglet.webexpansion.request.RequestHandler;
import org.bukkit.OfflinePlayer;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class WebExpansion extends PlaceholderExpansion {
    private RequestHandler requestHandler;

    @Override
    public String getIdentifier() {
        return "web";
    }

    @Override
    public String getAuthor() {
        return "PiggyPiglet";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean register() {
        final String dir = getPlaceholderAPI().getDataFolder().getPath() + "/web";

        try {
            ConfigUtil.generate(dir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.requestHandler = Framework.builder()
                .main(PlaceholderExpansion.class, this)
                .pckg("me.piggypiglet.webexpansion")
                .fileDir(dir)
                .file(true, "config", "/config.json", "config.json", Config.class)
                .build()
                .init().getInjector().getInstance(RequestHandler.class);

        return super.register();
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        return requestHandler.handle(p, params);
    }
}
