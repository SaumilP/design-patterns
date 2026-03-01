package design.patterns.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public abstract class PartyMemeberBase implements PartyMember {
    protected Party party;
    private static final Logger Log = LoggerFactory.getLogger( PartyMemeberBase.class );

    @Override
    public void joinParty(Party party) {
        Log.debug(this + " joins the party");
    }

    @Override
    public void partyAction(Action action) {
        StringBuffer actionString = new StringBuffer();
        actionString.append(this + "");
        switch (action){
            case ENEMY: actionString.append("runs for cover"); break;
            case GOLD: actionString.append("takes his share of gold"); break;
            case TALE: actionString.append("comes to listen"); break;
            case HUNT: actionString.append("arrives for dinner"); break;
        }
        Log.debug(actionString.toString());
    }

    @Override
    public void act(Action action) {
        if ( party != null ){
            Log.debug(this + " " + action.toString() );
            party.act(this, action);
        }
    }

    @Override public abstract String toString();
}