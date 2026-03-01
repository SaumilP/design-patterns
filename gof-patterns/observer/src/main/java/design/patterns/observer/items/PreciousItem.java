package design.patterns.observer.items;

/**
 * Item POJO containing item details
 */
public class PreciousItem extends Item {
    private String ownerName;
    private boolean isRareItem;

    public PreciousItem() {
        super("unknown", ItemType.UNKNOWN);
        isRareItem = false;
    }

    public PreciousItem(String name, ItemType type, boolean isRareItem ){
        super(name, type);
        this.isRareItem = isRareItem;
    }

    @Override public String toString() {
        return "PreciousItem{" +
               ", isRareItem=" + isRareItem +
               ", owernName='" + ownerName + '\'' +
               '}';
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public boolean isRareItem() {
        return isRareItem;
    }

    public void setRareItem(boolean isRareItem) {
        this.isRareItem = isRareItem;
    }
}
