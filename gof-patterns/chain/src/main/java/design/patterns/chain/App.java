package design.patterns.chain;

/**
 * Chain Design Pattern Client
 */
public class App {
    public static void main(String[] args){
        HiringProcess entryLevel = new EntryLevelHiringProcess(HiringProcess.ENTRY_LEVEL);
        HiringProcess midLevel = new MiddleLevelHiringProcess(HiringProcess.MIDDLE_LEVEL);
        HiringProcess seniorLevel = new SeniorLevelHiringProcess(HiringProcess.SENIOR_LEVEL);

        entryLevel.setNextProcess(midLevel);
        midLevel.setNextProcess(seniorLevel);

        entryLevel.process(HiringProcess.SENIOR_LEVEL);
        entryLevel.process(HiringProcess.MIDDLE_LEVEL);
        entryLevel.process(HiringProcess.ENTRY_LEVEL);
        entryLevel.process(HiringProcess.SENIOR_LEVEL);
    }
}
