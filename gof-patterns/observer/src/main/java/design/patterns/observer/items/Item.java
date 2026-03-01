package design.patterns.observer.items;

/**
 * Abstract Item class
 */
public abstract class Item {
    private String name;
    private ItemType type;

    protected Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

}
