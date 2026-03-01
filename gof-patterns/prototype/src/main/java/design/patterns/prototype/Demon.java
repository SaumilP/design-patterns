package design.patterns.prototype;

/**
 * Prototyped Class
 */
public class Demon extends Monster {
    private boolean hasPossessed;

    public Demon(String name) {
        super(name, MonsterType.DEMON);
        setCriteriaType(CriteriaType.EVIL);
        hasPossessed = false;
    }

    public Demon(String name, MonsterType type) {
        super(name, type);
        setCriteriaType(CriteriaType.EVIL);
        hasPossessed = false;
    }

    public boolean isHasPossessed() {
        return hasPossessed;
    }

    public void setHasPossessed(boolean hasPossessed) {
        this.hasPossessed = hasPossessed;
    }

    @Override public Demon clone() throws CloneNotSupportedException {
        System.out.println("Cloning a demon...");
        return (Demon)super.clone();
    }

    @Override public String toString() {
        String superString = super.toString();
        return "Demon{" +
               superString +
               ", hasPossessed=" + hasPossessed +
               '}';
    }
}
