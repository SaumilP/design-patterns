package design.patterns.pipes;

import design.patterns.pipes.models.DataContext;
import design.patterns.pipes.models.PipelineConfiguration;
import design.patterns.pipes.stages.Stage;
import org.apache.commons.lang3.Validate;

import java.util.LinkedList;

/**
 * Fluent interface based Pipeline Class
 */
public final class Pipeline {
    private PipelineConfiguration configuration;
    private static LinkedList<Stage> stages;
    private static DataContext processedContext;
    private static Pipeline INSTANCE;

    private Pipeline(){
        stages = new LinkedList<>();
        processedContext = null;
        configuration = new PipelineConfiguration();
    }

    private Pipeline(PipelineConfiguration configuration){
        if(configuration != null ){
            this.configuration = configuration;
        }
    }

    public static Pipeline create(PipelineConfiguration configuration){
        if(INSTANCE == null ){
            INSTANCE = new Pipeline(configuration);
        }
        return INSTANCE;
    }

    public static Pipeline create(){
        INSTANCE = new Pipeline();
        return INSTANCE;
    }

    public Pipeline use(Stage stage){
        if(stage != null ){
            stages.add(stage);
        }
        return this;
    }

    public DataContext execute(DataContext context) throws Exception {
        if( context != null ){
            processedContext = context;
            for (Stage pipelineStage : stages) {
                if(configuration.isMustValidateStageContext()){
                    validate(processedContext);
                }
                processedContext = pipelineStage.execute(processedContext);
            }
        }
        return processedContext;
    }

    public void validate(DataContext context){
        Validate.notNull(context, "Data context cannot be null for pipeline to be executed");
    }
}
