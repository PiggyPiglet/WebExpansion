package me.piggypiglet.webexpansion.request;

import com.google.inject.Inject;
import me.piggypiglet.webexpansion.utils.WebUtils;
import org.bukkit.OfflinePlayer;

import java.util.Arrays;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RequestHandler {
    @Inject private RequestManager requestManager;

    public String handle(OfflinePlayer player, String request) {
        String[] allParts = request.split("_");
        String[] parts = {allParts[0], String.join("_", Arrays.copyOfRange(allParts, 1, allParts.length))};

        try {
            return WebUtils.getStringEntity(requestManager.search(parts[0]).get(0).getUrl() + "/" + parts[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong :(";
        }
    }
}
