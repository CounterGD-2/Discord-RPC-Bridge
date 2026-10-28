package dev.countergd.discordmobilebridge.presence;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public record PresenceActivity(PresenceKind kind, String details, String state, Optional<Instant> startedAt) {
    public PresenceActivity {
        Objects.requireNonNull(kind, "kind");
        details = clean(details, 128);
        state = clean(state, 128);
        startedAt = startedAt == null ? Optional.empty() : startedAt;
    }
    private static String clean(String value, int max) {
        if (value == null) return "";
        String normalized = value.replaceAll("[\\p{Cntrl}]", " ").trim();
        return normalized.length() > max ? normalized.substring(0, max) : normalized;
    }
}
