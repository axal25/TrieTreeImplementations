package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.PatriciaTree;
import lombok.Getter;

@Getter
public class FileOps {
    private PatriciaTree owner;
    private FileOpsStrategy fileOpsStrategy;
    private WordStrategy wordStrategy;


    public FileOps(PatriciaTree owner, String filePath, String fileName, char charEOF, char charEOK) throws Exception {
        this.owner = owner;
        initWordStrategy();
        initFileOpsStrategy(filePath, fileName, charEOF, charEOK);
    }
    public FileOps(PatriciaTree owner, String filePath, String fileName, char charEOF, char charEOK, WordStrategy wordStrategy) throws Exception {
        this.owner = owner;
        initWordStrategy(wordStrategy);
        initFileOpsStrategy(filePath, fileName, charEOF, charEOK);
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
    private void initFileOpsStrategy(String filePath, String fileName, char charEOF, char charEOK) throws Exception {
        if (this.getWordStrategy() == WordStrategy.SINGLE)
            this.fileOpsStrategy = new WordSingleStrategy(this, filePath, fileName, charEOF, charEOK);
        else if (this.getWordStrategy() == WordStrategy.START_POSITION_TO_EOF)
            this.fileOpsStrategy = new WordStartPositionToEOFStrategy(this, filePath, fileName, charEOF, charEOK);
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
