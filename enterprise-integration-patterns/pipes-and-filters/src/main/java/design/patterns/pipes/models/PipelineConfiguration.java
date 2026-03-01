package design.patterns.pipes.models;

/**
 * Pipeline Configuration property holder class
 */
public class PipelineConfiguration {
    private int replacementCharIndexGap;
    private boolean mustValidateStageContext;
    private boolean mustReadFile;
    private String inputFileName;
    private boolean mustGenerateFile;
    private boolean outputFileName;

    public PipelineConfiguration() {
    }

    public int getReplacementCharIndexGap() {
        return replacementCharIndexGap;
    }

    public void setReplacementCharIndexGap(int replacementCharIndexGap) {
        this.replacementCharIndexGap = replacementCharIndexGap;
    }

    public boolean isMustValidateStageContext() {
        return mustValidateStageContext;
    }

    public void setMustValidateStageContext(boolean mustValidateStageContext) {
        this.mustValidateStageContext = mustValidateStageContext;
    }

    public boolean isMustReadFile() {
        return mustReadFile;
    }

    public void setMustReadFile(boolean mustReadFile) {
        this.mustReadFile = mustReadFile;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public boolean isMustGenerateFile() {
        return mustGenerateFile;
    }

    public void setMustGenerateFile(boolean mustGenerateFile) {
        this.mustGenerateFile = mustGenerateFile;
    }

    public boolean isOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(boolean outputFileName) {
        this.outputFileName = outputFileName;
    }
}
