package design.patterns.servant;

/**
 * Batman Class....
 */
public class Batman implements RichRoyalty {

    private boolean checkedReport = false;
    private boolean isHungry = false;
    private boolean isReadyForNightTroll = false;
    private boolean isThursty = false;

    @Override public void getDrink() {
        isThursty = true;
    }

    @Override public void needBatMobile() {
        isReadyForNightTroll = true;
    }

    @Override public void getJuice() {
        isThursty = true;
    }

    @Override public void readyFood() {
        isHungry = true;
    }

    @Override public void checkOnMaids() {
        checkedReport = true;
    }
}
