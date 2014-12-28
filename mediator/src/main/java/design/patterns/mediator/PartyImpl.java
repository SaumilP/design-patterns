package design.patterns.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class PartyImpl implements Party {

    private List<PartyMember> members;

    public PartyImpl() {
        members = new ArrayList<>();
    }

    @Override
    public void addMember(PartyMember member) {
        members.add( member );
        member.joinParty( this );
    }

    @Override
    public void act(PartyMember member, Action action) {
        for (PartyMember partyMember : members) {
            if  (partyMember != member){
                partyMember.partyAction( action );
            }
        }
    }
}
