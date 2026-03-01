package design.patterns.prototype;

/**
 * Abstract Creature Cloning Prototype
 */
public interface SupernaturalCreatures extends Cloneable {

    public abstract SupernaturalCreatures clone() throws CloneNotSupportedException;
}
