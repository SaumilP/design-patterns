package design.patterns.locators.bros;

import design.patterns.locators.services.FindGuyService;
import design.patterns.locators.services.ServiceType;

/**
 * Guy helps barney find guy who knows a guy
 */
public class GuyFinderGuy extends BarneyGuy {
    private String contactNo;

    public GuyFinderGuy(String name, String contactNo) {
        super(name);
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override public void addServiceType(ServiceType type) {
        serviceTypes.add(type);
        setService( new FindGuyService() );
    }
}
