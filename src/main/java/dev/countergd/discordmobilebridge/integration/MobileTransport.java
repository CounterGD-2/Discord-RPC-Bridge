package dev.countergd.discordmobilebridge.integration;

import dev.countergd.discordmobilebridge.presence.PresenceActivity;

/** Future official/supported mobile transport; desktop IPC is not assumed to work on Android. */
public interface MobileTransport extends AutoCloseable {
    boolean isSupportedOnCurrentPlatform();
    void publish(PresenceActivity activity);
    void clear();
    @Override default void close() { clear(); }
}
