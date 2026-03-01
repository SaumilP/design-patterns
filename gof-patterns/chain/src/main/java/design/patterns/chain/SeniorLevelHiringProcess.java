package design.patterns.chain;

/**
 * Senior level hiring process class
 */
public class SeniorLevelHiringProcess extends HiringProcess {

    public SeniorLevelHiringProcess(int level) {
        this.level = level;
    }

    @Override protected void printHiringDecision() {
        System.out.println("Interviewee is hired as a senor level employee");
    }
}
