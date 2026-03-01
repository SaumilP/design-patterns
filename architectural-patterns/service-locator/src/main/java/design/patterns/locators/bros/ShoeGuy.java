package design.patterns.locators.bros;

import design.patterns.locators.services.FindShoeService;
import design.patterns.locators.services.ServiceType;

/**
 * Shoe Guy helps barney find best shoe shops
 */
public class ShoeGuy extends BarneyGuy {
    private String contactNo;

    public ShoeGuy(String name, String contactNo) {
        super(name);
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override public void addServiceType(ServiceType type) {
        serviceTypes.add(type);
        setService( new FindShoeService() );
    }
}
