package design.patterns.prototype;

/**
 * Prototyped Class
 */
public class Generic extends Monster {

    public Generic(String name) {
        super(name, MonsterType.GENERIC);
    }

    public Generic(String name, MonsterType type) {
        super(name, type);
    }

    @Override public Generic clone() throws CloneNotSupportedException {
        System.out.println("Cloning a Generic Monster...");
        return (Generic)super.clone();
    }
}
