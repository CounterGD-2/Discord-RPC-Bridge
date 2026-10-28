package dev.countergd.discordmobilebridge.integration;

import dev.countergd.discordmobilebridge.presence.PresenceActivity;

/** Inert extension point until DiscordPresence's documented public API is verified. */
public final class DiscordPresenceAdapter implements ActivityPublisher {
    @Override public String id() { return "discord-presence"; }
    @Override public boolean isAvailable() { return false; }
    @Override public void publish(PresenceActivity activity) { /* deliberately inert */ }
    @Override public void clear() { /* deliberately inert */ }
}
