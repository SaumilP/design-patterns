package design.patterns.cqrs;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CqrsTest {
    @Test
    void commandEmitsEventsQuerySeesProjectedState() {
        EventStore store = new EventStore();
        PlayerReadModel readModel = new PlayerReadModel();
        Projector projector = new Projector(store, readModel);

        Clock fixed = Clock.fixed(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        PlayerCommandService commands = new PlayerCommandService(store, fixed);

        String id = "p1";
        commands.createPlayer(id, "Knight");
        commands.changeDisplayName(id, "Knight_2");

        projector.projectAggregate(id);
        var view = readModel.get(id);
        assertTrue(view.isPresent());
        assertEquals("Knight_2", view.get().displayName());
    }
}

