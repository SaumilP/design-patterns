package design.pattern.decorator.armors;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Shirt extends Armor {

    public Shirt() {
        setDefense(5);
        setSellingPrice(4);
        setType(ArmorType.BODY_ARMOR);
        setName("Cloth Shirt");
    }
}
