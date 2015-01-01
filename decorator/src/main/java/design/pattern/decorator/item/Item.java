package design.pattern.decorator.item;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Item {
    private String name;
    private String description;
    private ItemRarity rarity;
    private boolean hasBeenIdentified;
    private boolean suffixBeingUsed;
    private boolean prefixBeignUsed;
    private int sellingPrice;
    private String namePrefix;
    private String nameSuffix;

    public void modifyDescription(String value){
        this.description = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public void setRarity(ItemRarity rarity) {
        this.rarity = rarity;
    }

    public boolean isHasBeenIdentified() {
        return hasBeenIdentified;
    }

    public void setHasBeenIdentified(boolean hasBeenIdentified) {
        this.hasBeenIdentified = hasBeenIdentified;
    }

    public boolean isSuffixBeingUsed() {
        return suffixBeingUsed;
    }

    public void setSuffixBeingUsed(boolean suffixBeingUsed) {
        this.suffixBeingUsed = suffixBeingUsed;
    }

    public boolean isPrefixBeignUsed() {
        return prefixBeignUsed;
    }

    public void setPrefixBeignUsed(boolean prefixBeignUsed) {
        this.prefixBeignUsed = prefixBeignUsed;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }
}
