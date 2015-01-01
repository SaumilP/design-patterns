package design.pattern.decorator.armors;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Boot extends Armor {

    public Boot() {
        setDefense(3);
        setSellingPrice(4);
        setType(ArmorType.FEET_ARMOR);
        setName("Boots");
    }
}
