package design.patterns;

import design.patterns.factories.ElfKingdomFactory;
import design.patterns.factories.KingdomFactory;
import design.patterns.factories.OrcKingdomFactory;
import design.patterns.adapter.interfaces.Army;
import design.patterns.adapter.interfaces.Castle;
import design.patterns.adapter.interfaces.King;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class App {

    private static final Logger Log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ){
        createKingdom( new ElfKingdomFactory());
        createKingdom( new OrcKingdomFactory());
    }

    private static void createKingdom(KingdomFactory kingdomFactory) {
        King king = kingdomFactory.createKing();
        Castle castle = kingdomFactory.createCastle();
        Army army = kingdomFactory.createArmy();

        Log.debug("The kingdom was created");
    }
}
