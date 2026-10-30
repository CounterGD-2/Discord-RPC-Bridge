package dev.countergd.discordmobilebridge.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import dev.countergd.discordmobilebridge.DiscordMobileBridge;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class BridgeConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Path.of("config", "discordmobilebridge.json");

    public boolean enabled = true;
    public boolean showServerAddress = false;
    public boolean showWorldName = false;
    public boolean showElapsedTime = true;
    public int refreshIntervalTicks = 40;
    public boolean simpleRpcAdapterEnabled = false;
    public boolean discordPresenceAdapterEnabled = false;
    public boolean mobileTransportEnabled = false;

    public static BridgeConfig load() {
        try {
            Files.createDirectories(PATH.getParent());
            if (Files.notExists(PATH)) {
                BridgeConfig defaults = new BridgeConfig();
                defaults.save();
                return defaults;
            }
            BridgeConfig loaded = GSON.fromJson(Files.readString(PATH), BridgeConfig.class);
            return loaded == null ? new BridgeConfig() : loaded;
        } catch (IOException | JsonParseException exception) {
            System.err.println("[DiscordMobileBridge] Config read failed; using defaults: " + exception);
            return new BridgeConfig();
        }
    }

    public synchronized void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(this));
        } catch (IOException exception) {
            System.err.println("[DiscordMobileBridge] Config save failed: " + exception);
        }
        DiscordMobileBridge.PRESENCE.setEnabled(enabled);
    }
}
