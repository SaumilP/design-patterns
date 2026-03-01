package design.patterns.observer.items;

/**
 * Precious Items
 */
public enum ItemType {
    UNKNOWN("Unknown Item"), ONE_RING("Rulling Ring"), ARKENSTONE("King Jewell");

    private String name;
    ItemType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
