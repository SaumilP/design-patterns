package design.patterns.pipes.stages;

import design.patterns.pipes.ProcessingException;
import design.patterns.pipes.models.DataContext;

/**
 * Pipeline stage interface used to execute configured step
 */
public interface Stage {
    DataContext execute(DataContext context) throws ProcessingException;
}
