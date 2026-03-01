package design.patterns.locators.bros;

import design.patterns.locators.services.FindSuiteService;
import design.patterns.locators.services.ServiceType;

/**
 * Suite Guy helps barney find Tailor shops
 */
public class SuiteGuy extends BarneyGuy {
    private String contactNo;

    public SuiteGuy(String name, String contactNo) {
        super(name);
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override public void addServiceType(ServiceType type) {
        serviceTypes.add(type);
        setService( new FindSuiteService() );
    }
}
