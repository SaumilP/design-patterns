package design.patterns.chain;

/**
 * Abstracted Hiring Process
 */
public abstract class HiringProcess {
    public static final int ENTRY_LEVEL = 1;
    public static final int MIDDLE_LEVEL = 2;
    public static final int SENIOR_LEVEL = 3;

    protected int level;
    protected HiringProcess nextProcess;

    public void setNextProcess(HiringProcess nextProcess) {
        this.nextProcess = nextProcess;
    }

    public void process(int level){
        if( this.level >= level){
            printHiringDecision();
        } else{
            nextProcess.process(level);
        }
    }

    protected abstract void printHiringDecision();
}
