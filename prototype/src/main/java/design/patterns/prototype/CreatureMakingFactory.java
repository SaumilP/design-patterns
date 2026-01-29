package design.patterns.prototype;

import java.util.EnumMap;
import java.util.Map;

/**
 * Prototype Factory
 */
public class CreatureMakingFactory {
    private static Map<MonsterType,Monster> monsters;

    static {
        monsters = new EnumMap<>(MonsterType.class);
        monsters.put(MonsterType.DEMON, new Demon("Abaddon"));
        monsters.put(MonsterType.GHOST, new Ghost("Bloody Mary"));
        monsters.put(MonsterType.GENERIC, new Demon("Ariel"));
    }

    public static Monster spawnMonster(final MonsterType monsterType) throws CloneNotSupportedException {
        return (Monster)monsters.get(monsterType).clone();
    }
}
