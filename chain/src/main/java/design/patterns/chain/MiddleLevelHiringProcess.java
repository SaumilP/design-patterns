package design.patterns.chain;

/**
 * Intermediate level Hiring Process Class
 */
public class MiddleLevelHiringProcess extends HiringProcess {

    public MiddleLevelHiringProcess(int level) {
        this.level = level;
    }

    @Override protected void printHiringDecision() {
        System.out.println("Interviewee is hired as a intermediate level employee");
    }
}
