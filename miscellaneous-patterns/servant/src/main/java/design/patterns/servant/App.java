package design.patterns.servant;

import java.util.ArrayList;

/**
 * Servant/Worker Design Pattern Client
 */
public class App {

    static Butler alfred = new Butler("Alfred");

    public static void dailyActions( Butler butler){
        Batman batman = new Batman();

        ArrayList<RichRoyalty> guests = new ArrayList<>();
        guests.add(batman);

        butler.feed(batman);
        butler.readyTheBatMobileForHunt(batman);
        butler.prepareJuice(batman);
        butler.supervise(batman);
    }

    public static void main (String[] args){
        dailyActions(alfred);
    }
}
