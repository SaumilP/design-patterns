package design.patterns;

import design.patterns.factories.ElfKingdomFactory;
import design.patterns.factories.KingdomFactory;
import design.patterns.interfaces.Army;
import design.patterns.interfaces.Castle;
import design.patterns.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class App {

    private static final Logger Log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ){
        createKingdom( new ElfKingdomFactory());
    }

    private static void createKingdom(KingdomFactory kingdomFactory) {
        King king = kingdomFactory.createKing();
        Castle castle = kingdomFactory.createCastle();
        Army army = kingdomFactory.createArmy();

        Log.append();
    }
}
