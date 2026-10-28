package dev.countergd.discordmobilebridge;

import dev.countergd.discordmobilebridge.config.BridgeConfig;
import dev.countergd.discordmobilebridge.config.BridgeConfigScreen;
import dev.countergd.discordmobilebridge.presence.PresenceManager;
import dev.countergd.discordmobilebridge.client.ClientActivityCollector;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(DiscordMobileBridge.MOD_ID)
public final class DiscordMobileBridge {
    public static final String MOD_ID = "discordmobilebridge";
    public static final PresenceManager PRESENCE = new PresenceManager();
    public static final BridgeConfig CONFIG = BridgeConfig.load();

    public DiscordMobileBridge(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class,
            (modContainer, parent) -> BridgeConfigScreen.create(parent));
        ClientActivityCollector.register();
    }
}
