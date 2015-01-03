package design.patterns.prototype;

/**
 * Prototyped Class
 */
public class Ghost extends Monster {
    private boolean isVisible;
    private int visibility;
    private int health;
    private int speed;

    public Ghost(String name) {
        super(name, MonsterType.GHOST);
        setCriteriaType(CriteriaType.HARMLESS);
        isVisible = false;
        visibility = 30;
        health = MAX_HEALTH;
        speed = MAX_SPEED;
    }

    public Ghost(String name, MonsterType type) {
        super(name, type);
        isVisible = false;
        visibility = 30;
        health = MAX_HEALTH;
        speed = MAX_SPEED;
    }

    public Ghost(String name, MonsterType type, boolean isVisible, int visibility, int health, int speed) {
        super(name, type);
        this.isVisible = isVisible;
        this.visibility = visibility;
        this.health = health;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @Override public Monster clone() throws CloneNotSupportedException {
        System.out.println("Cloning Ghost ...");
        return (Ghost)super.clone();
    }

    @Override public String toString() {
        String superString = super.toString();
        return "Ghost{" +
               superString +
               ", isVisible=" + isVisible +
               ", visibility=" + visibility +
               ", health=" + health +
               ", speed=" + speed +
               '}';
    }
}
