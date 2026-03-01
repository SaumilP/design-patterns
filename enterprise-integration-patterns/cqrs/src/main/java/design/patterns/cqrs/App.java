package design.patterns.cqrs;

import java.time.Clock;

public final class App {
    public static void main(String[] args) {
        // An example: command side emits events; query side serves player profile reads.
        EventStore store = new EventStore();
        PlayerReadModel readModel = new PlayerReadModel();
        Projector projector = new Projector(store, readModel);
        PlayerCommandService commands = new PlayerCommandService(store, Clock.systemUTC());

        String playerId = "player-42";
        commands.createPlayer(playerId, "RogueMage");
        commands.changeDisplayName(playerId, "RogueMage_2");

        projector.projectAggregate(playerId);
        System.out.println(readModel.get(playerId).orElseThrow());
    }
}

