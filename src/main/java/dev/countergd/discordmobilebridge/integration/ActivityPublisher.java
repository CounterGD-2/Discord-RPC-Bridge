package dev.countergd.discordmobilebridge.integration;
import dev.countergd.discordmobilebridge.presence.PresenceActivity;
public interface ActivityPublisher extends AutoCloseable {
    String id();
    boolean isAvailable();
    void publish(PresenceActivity activity);
    void clear();
    @Override default void close() { clear(); }
}
