package design.patterns.mediator;

/**
 * Created by PATEL1 on 12/28/14.
 */
public interface Party {
    void addMember(PartyMember member);
    void act(PartyMember member, Action action);
}
