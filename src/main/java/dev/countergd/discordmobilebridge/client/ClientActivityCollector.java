package dev.countergd.discordmobilebridge.client;

import dev.countergd.discordmobilebridge.DiscordMobileBridge;
import dev.countergd.discordmobilebridge.presence.PresenceActivity;
import dev.countergd.discordmobilebridge.presence.PresenceKind;
import dev.countergd.discordmobilebridge.presence.PresenceSource;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import java.time.Instant;
import java.util.Optional;

public final class ClientActivityCollector implements PresenceSource {
    private static final ClientActivityCollector INSTANCE = new ClientActivityCollector();
    private long ticks;
    private ClientActivityCollector() {}

    public static void register() {
        DiscordMobileBridge.PRESENCE.register(INSTANCE);
        NeoForge.EVENT_BUS.register(INSTANCE);
    }
    @Override public String id() { return "minecraft-client"; }
    @Override public int priority() { return 100; }

    @Override public Optional<PresenceActivity> currentActivity() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null)
            return Optional.of(new PresenceActivity(PresenceKind.MENU, "Minecraft", "In menus", Optional.empty()));
        return Optional.of(new PresenceActivity(PresenceKind.OTHER, "Playing Minecraft", "In a world", Optional.of(Instant.now())));
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        ticks++;
        int interval = Math.max(10, DiscordMobileBridge.CONFIG.refreshIntervalTicks);
        if (ticks % interval == 0)
            DiscordMobileBridge.PRESENCE.setEnabled(DiscordMobileBridge.CONFIG.enabled);
    }
}
