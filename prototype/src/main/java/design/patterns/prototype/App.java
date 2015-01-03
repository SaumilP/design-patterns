package design.patterns.prototype;

/**
 * Prototype Design Pattern Client
 */
public class App {
    public static void main (String[] args){
        try {
            Monster spirit = CreatureMakingFactory.spawnMonster(MonsterType.GENERIC);
            System.out.println( String.format("Monster[%s] created\n", spirit.toString() ));

            Monster casperGhost = CreatureMakingFactory.spawnMonster(MonsterType.GHOST);
            System.out.println( String.format("Monster[%s] created\n", casperGhost.toString()));

            Monster abaddon = CreatureMakingFactory.spawnMonster(MonsterType.DEMON);
            System.out.println( String.format("Monster[%s] created\n", abaddon.toString()));
        } catch (CloneNotSupportedException e) {
            System.out.println("Failed to clone Monsters - " + e);
        }

    }
}
