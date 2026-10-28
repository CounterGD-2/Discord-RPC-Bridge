package dev.countergd.discordmobilebridge.integration;

import dev.countergd.discordmobilebridge.presence.PresenceActivity;

/** Inert extension point until Simple RPC's documented public API is verified. */
public final class SimpleRpcAdapter implements ActivityPublisher {
    @Override public String id() { return "simple-rpc"; }
    @Override public boolean isAvailable() { return false; }
    @Override public void publish(PresenceActivity activity) { /* deliberately inert */ }
    @Override public void clear() { /* deliberately inert */ }
}
