package design.patterns.pipes.stages;

import design.patterns.pipes.ProcessingException;
import design.patterns.pipes.models.DataContext;

/**
 * Printer Class responsible for priting context to STD OUT
 */
public class Printer implements Stage {

    private String prevStageName;

    public Printer(String prevStageName) {
        this.prevStageName = prevStageName;
    }

    @Override public DataContext execute(DataContext context) throws ProcessingException {
        if(context!=null){
            System.out.println(String.format("\nAfter %s Context => \n%s",prevStageName,context.toString()) );
        }
        return context;
    }
}
