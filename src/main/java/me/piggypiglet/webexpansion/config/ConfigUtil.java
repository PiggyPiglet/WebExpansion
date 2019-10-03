package me.piggypiglet.webexpansion.config;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfigUtil {
    private static final String URL = "https://rpf.piggypiglet.me/webexpansion/config.json";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void generate(String dir) throws Exception {
        File file = new File(dir, "config.json");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();

            final URL url = new URL(URL);
            ReadableByteChannel channel = Channels.newChannel(url.openStream());
            FileOutputStream out = new FileOutputStream(file.getPath());
            out.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
        }
    }
}
