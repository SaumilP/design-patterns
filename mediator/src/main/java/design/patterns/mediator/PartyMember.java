package design.patterns.mediator;

/**
 * Member interactions with Party
 */
public interface PartyMember {
    void joinParty(Party party);
    void partyAction(Action action);
    void act(Action action);
}
