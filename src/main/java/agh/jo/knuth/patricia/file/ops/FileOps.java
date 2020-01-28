package agh.jo.knuth.patricia.file.ops;

import lombok.Getter;

@Getter
public class FileOps {
    private FileOpsStrategy fileOpsStrategy;
    private WordStrategy wordStrategy;


    public FileOps(String filePath, String fileName) throws Exception {
        initWordStrategy();
        initFileOpsStrategy(filePath, fileName);
    }
    public FileOps(String filePath, String fileName, WordStrategy wordStrategy) throws Exception {
        initWordStrategy(wordStrategy);
        initFileOpsStrategy(filePath, fileName);
    }

    private void setWordStrategy(WordStrategy wordStrategy) { this.wordStrategy = wordStrategy;}
    private void initWordStrategy() throws Exception {
        this.wordStrategy = WordStrategy.START_POSITION_TO_EOF;
    }
    private void initWordStrategy(WordStrategy wordStrategy) throws Exception {
        if(wordStrategy == WordStrategy.SINGLE) this.wordStrategy = wordStrategy;
        else if(wordStrategy == WordStrategy.START_POSITION_TO_EOF) this.wordStrategy = wordStrategy;
        else throw new Exception("Bad word strategy: " + this.getWordStrategy());
    }

    private void setFileOpsStrategy(FileOpsStrategy fileOpsStrategy) { this.fileOpsStrategy = fileOpsStrategy; }
    private void initFileOpsStrategy(String filePath, String fileName) throws Exception {
        if (this.getWordStrategy() == WordStrategy.SINGLE)
            this.fileOpsStrategy = new SingleWordStrategy(filePath, fileName);
        else if (this.getWordStrategy() == WordStrategy.START_POSITION_TO_EOF)
            this.fileOpsStrategy = new WordStartPositionToEOFStrategy(filePath, fileName);
        else throw new Exception("Bad word strategy: " + this.getWordStrategy());
    }

    @Override
    public String toString() {
        return "FileOps{" +
                "\n\twordStrategy=" + wordStrategy +
                ",\n\tfileOpsStrategy=" + fileOpsStrategy +
                "\n\t}";
    }
}
