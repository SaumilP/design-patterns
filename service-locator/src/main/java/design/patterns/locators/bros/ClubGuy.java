package design.patterns.locators.bros;

import design.patterns.locators.services.FindClubService;
import design.patterns.locators.services.ServiceType;

/**
 * Club Guy helps barney find clubs
 */
public class ClubGuy extends BarneyGuy {
    private String contactNo;

    public ClubGuy(String name, String contactNo) {
        super(name);
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override public void addServiceType(ServiceType type) {
        serviceTypes.add(type);
        setService( new FindClubService() );
    }
}
