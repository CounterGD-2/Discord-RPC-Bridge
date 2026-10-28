package dev.countergd.discordmobilebridge.presence;
import java.util.Optional;
public interface PresenceSource {
    String id();
    int priority();
    Optional<PresenceActivity> currentActivity();
}
