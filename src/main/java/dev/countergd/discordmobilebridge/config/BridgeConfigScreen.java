package dev.countergd.discordmobilebridge.config;

import dev.countergd.discordmobilebridge.DiscordMobileBridge;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class BridgeConfigScreen {
    private BridgeConfigScreen() {}

    public static Screen create(Screen parent) {
        BridgeConfig config = DiscordMobileBridge.CONFIG;
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent)
            .setTitle(Component.literal("Discord Mobile Bridge"));
        ConfigEntryBuilder entries = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));
        general.addEntry(entries.startBooleanToggle(Component.literal("Enable presence bridge"), config.enabled)
            .setDefaultValue(true).setSaveConsumer(v -> config.enabled = v).build());
        general.addEntry(entries.startBooleanToggle(Component.literal("Show server address"), config.showServerAddress)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Privacy-sensitive. Leave off unless you explicitly want it displayed."))
            .setSaveConsumer(v -> config.showServerAddress = v).build());
        general.addEntry(entries.startBooleanToggle(Component.literal("Show world name"), config.showWorldName)
            .setDefaultValue(false).setSaveConsumer(v -> config.showWorldName = v).build());
        general.addEntry(entries.startBooleanToggle(Component.literal("Show elapsed time"), config.showElapsedTime)
            .setDefaultValue(true).setSaveConsumer(v -> config.showElapsedTime = v).build());
        general.addEntry(entries.startIntSlider(Component.literal("Refresh interval (ticks)"), config.refreshIntervalTicks, 10, 200)
            .setDefaultValue(40).setSaveConsumer(v -> config.refreshIntervalTicks = v).build());

        ConfigCategory integrations = builder.getOrCreateCategory(Component.literal("Integrations"));
        integrations.addEntry(entries.startBooleanToggle(Component.literal("Simple RPC adapter"), config.simpleRpcAdapterEnabled)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Inactive until a documented compatible provider is installed."))
            .setSaveConsumer(v -> config.simpleRpcAdapterEnabled = v).build());
        integrations.addEntry(entries.startBooleanToggle(Component.literal("DiscordPresence adapter"), config.discordPresenceAdapterEnabled)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Inactive until a documented compatible provider is installed."))
            .setSaveConsumer(v -> config.discordPresenceAdapterEnabled = v).build());
        integrations.addEntry(entries.startBooleanToggle(Component.literal("Mobile transport"), config.mobileTransportEnabled)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Experimental placeholder; no transport is enabled in this scaffold."))
            .setSaveConsumer(v -> config.mobileTransportEnabled = v).build());

        builder.setSavingRunnable(config::save);
        return builder.build();
    }
}
