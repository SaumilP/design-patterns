package design.pattern.iterator;

/**
 * Weapon Class
 */
public class Weapon {
    private String name;
    private WeaponType type;
    private boolean isPoisonous;
    private int damage;
    private HandType handType;

    public Weapon(String name,
                  WeaponType type,
                  boolean isPoisonous,
                  int damage,
                  HandType handType) {
        this.name = name;
        this.type = type;
        this.isPoisonous = isPoisonous;
        this.damage = damage;
        this.handType = handType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public void setPoisonous(boolean isPoisonous) {
        this.isPoisonous = isPoisonous;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public HandType getHandType() {
        return handType;
    }

    public void setHandType(HandType handType) {
        this.handType = handType;
    }

    @Override public String toString() {
        return "Weapon{" +
               "name='" + name + '\'' +
               ", type=" + type +
               ", isPoisonous=" + isPoisonous +
               ", damage=" + damage +
               ", handType=" + handType +
               '}';
    }
}
