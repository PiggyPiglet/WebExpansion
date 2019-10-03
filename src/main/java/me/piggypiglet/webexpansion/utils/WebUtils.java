package me.piggypiglet.webexpansion.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class WebUtils {
    public static String getStringEntity(String URL) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(URL).openConnection().getInputStream(), StandardCharsets.UTF_8));
        final StringBuilder builder = new StringBuilder();

        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            builder.append(inputLine);
        }

        reader.close();
        return builder.toString();
    }
}
