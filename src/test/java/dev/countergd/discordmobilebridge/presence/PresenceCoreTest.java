package dev.countergd.discordmobilebridge.presence;

import java.util.Optional;

public final class PresenceCoreTest {
    public static void main(String[] args) {
        PresenceManager manager = new PresenceManager();
        PresenceActivity low = new PresenceActivity(PresenceKind.MENU, "Menu", "", Optional.empty());
        PresenceActivity high = new PresenceActivity(PresenceKind.OTHER, "Playing", "World", Optional.empty());
        manager.register(new PresenceSource() {
            public String id() { return "low"; }
            public int priority() { return 1; }
            public Optional<PresenceActivity> currentActivity() { return Optional.of(low); }
        });
        manager.register(new PresenceSource() {
            public String id() { return "high"; }
            public int priority() { return 5; }
            public Optional<PresenceActivity> currentActivity() { return Optional.of(high); }
        });
        if (manager.currentActivity().orElseThrow() != high) throw new AssertionError("Priority arbitration failed");
        manager.setEnabled(false);
        if (manager.currentActivity().isPresent()) throw new AssertionError("Disable failed");
        manager.setEnabled(true);
        if (!manager.currentActivity().isPresent()) throw new AssertionError("Re-enable failed");
        PresenceActivity cleaned = new PresenceActivity(PresenceKind.OTHER, "  hello\nworld  ", null, null);
        if (!cleaned.details().equals("hello world")) throw new AssertionError("Text normalization failed");
        System.out.println("PresenceCoreTest: OK");
    }
}
