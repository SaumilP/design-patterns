package design.patterns.pipes.models;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Data Context Class used to hold data stream
 */
public class DataContext {

    private List<String> sentences;

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    @Override public String toString() {
        return StringUtils.join(sentences,'\n');
    }
}
