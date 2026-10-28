package dev.countergd.discordmobilebridge.presence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class PresenceManager {
    private final Map<String, PresenceSource> sources = new LinkedHashMap<>();
    private boolean enabled = true;

    public synchronized void register(PresenceSource source) {
        if (source == null || source.id() == null || source.id().isBlank())
            throw new IllegalArgumentException("Presence source needs a stable id");
        if (sources.putIfAbsent(source.id(), source) != null)
            throw new IllegalArgumentException("Duplicate presence source: " + source.id());
    }
    public synchronized void unregister(String id) { sources.remove(id); }
    public synchronized void setEnabled(boolean value) { enabled = value; }
    public synchronized boolean isEnabled() { return enabled; }

    public synchronized Optional<PresenceActivity> currentActivity() {
        if (!enabled) return Optional.empty();
        return new ArrayList<>(sources.values()).stream()
            .sorted(Comparator.comparingInt(PresenceSource::priority).reversed())
            .map(PresenceSource::currentActivity)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
    }
}
