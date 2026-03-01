package design.patterns.prototype;

/**
 * Monster Class
 */
public abstract class Monster implements SupernaturalCreatures {
    private String name;
    private MonsterType type;
    private CriteriaType criteriaType;

    protected static final int MAX_HEALTH = 100;
    protected static final int MAX_SPEED = 30;

    protected Monster(String name, MonsterType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonsterType getType() {
        return type;
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    public CriteriaType getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(CriteriaType criteriaType) {
        this.criteriaType = criteriaType;
    }

    @Override public Monster clone() throws CloneNotSupportedException {
        return (Monster)super.clone();
    }

    @Override public String toString() {
        return "Monster{" +
               "name='" + name + '\'' +
               ", type=" + type +
               ", criteriaType=" + criteriaType +
               '}';
    }
}
