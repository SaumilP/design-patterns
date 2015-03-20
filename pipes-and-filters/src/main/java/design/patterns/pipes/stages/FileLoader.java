package design.patterns.pipes.stages;

import design.patterns.pipes.ProcessingException;
import design.patterns.pipes.models.DataContext;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * File Loader class responsible for reading the file
 */
public class FileLoader implements Stage {
    private boolean mustLoadFile;
    private String inputFileName;

    public FileLoader(String fileName) {
        if(StringUtils.isNotEmpty(fileName)){
            mustLoadFile = true;
            inputFileName = fileName;
        }
    }

    @Override public DataContext execute(DataContext context) throws ProcessingException {
        if(mustLoadFile){
            List<String> sentenceList = new ArrayList<>();
            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream in = classloader.getResourceAsStream(inputFileName);

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    sentenceList.add(line);
                }

                context.setSentences(sentenceList);
            } catch (Exception ex){
                throw new ProcessingException("Failed to action FileLoader Stage", ex);
            }
        }
        return context;
    }
}
