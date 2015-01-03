package design.patterns.chain;

/**
 * Entry level hiring process
 */
public class EntryLevelHiringProcess extends HiringProcess {

    public EntryLevelHiringProcess(int level) {
        this.level = level;
    }

    @Override protected void printHiringDecision() {
        System.out.println("Interviewee is hired as an entry level employee");
    }
}
