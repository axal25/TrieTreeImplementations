package agh.jo.knuth.patricia;

import agh.jo.knuth.patricia.file.ops.FileOps;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import agh.jo.utils.exceptions.NotForUseException;
import lombok.Getter;

@Getter
public class PatriciaTree {
    private FileOps fileOps;
    private MixMachine mixMachine;
    private LookUpLogic lookUpLogic;
    private InsertLogic insertLogic;
    private SearchLogic searchLogic;
    private PatriciaNode header;
    private int nextNodeIdToInsert;

    public static final boolean printProcessingInfo = false;
    public static final boolean printNonCriticalExceptionInfo = false;

    /** Constructors **/

    private PatriciaTree() throws NotForUseException { throw new NotForUseException(this.getClass().getName(), "PatriciaTree()", "Not for use, implementation not possible."); }
    public PatriciaTree(String filePath, String fileName, char charEOF, char charEOK) throws Exception {
        this(filePath, fileName, charEOF, charEOK, WordStrategy.START_POSITION_TO_EOF, Encoding.JAVA);
    }
    public PatriciaTree(String filePath, String fileName, char charEOF, char charEOK, Encoding encoding) throws Exception {
        setFileOps(filePath, fileName, charEOF, charEOK);
        setMixMachine(encoding, charEOF, charEOK);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }
    public PatriciaTree(String filePath, String fileName, char charEOF, char charEOK, Encoding encoding, int amountOfBits) throws Exception {
        setFileOps(filePath, fileName, charEOF, charEOK);
        setMixMachine(encoding, amountOfBits, charEOF, charEOK);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }
    public PatriciaTree(String filePath, String fileName, char charEOF, char charEOK, WordStrategy wordStrategy, Encoding encoding) throws Exception {
        setFileOps(filePath, fileName, charEOF, charEOK, wordStrategy);
        setMixMachine(encoding, charEOF, charEOK);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }
    public PatriciaTree(String filePath, String fileName, char charEOF, char charEOK, WordStrategy wordStrategy, Encoding encoding, int amountOfBits) throws Exception {
        setFileOps(filePath, fileName, charEOF, charEOK, wordStrategy);
        setMixMachine(encoding, amountOfBits, charEOF, charEOK);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }

    /** Setters **/

    private void setFileOps(String filePath, String fileName, char charEOF, char charEOK) throws Exception {
        this.fileOps = new FileOps(this, filePath, fileName, charEOF, charEOK);
    }

    private void setFileOps(String filePath, String fileName, char charEOF, char charEOK, WordStrategy wordStrategy) throws Exception {
        this.fileOps = new FileOps(this, filePath, fileName, charEOF, charEOK, wordStrategy);
    }

    private void setMixMachine(Encoding encoding, int amountOfBits, char charEOF, char charEOK) throws Exception { this.mixMachine = new MixMachine(encoding, amountOfBits, charEOF, charEOK); }
    private void setMixMachine(Encoding encoding, char charEOF, char charEOK) throws Exception { this.mixMachine = new MixMachine(encoding, charEOF, charEOK); }

    private void setHeader(PatriciaNode header) throws Exception { setHeader(); }
    private void setHeader() throws Exception {
        int key;
        if(this.fileOps.getFileOpsStrategy().isCharExistFromFileAtPosition(0)) key = 0;
        else throw new Exception("Could not create header. Character at position 0 did not exists.");
        int skip = 0;
        int id = this.getNextNodeIdToInsert();
        this.setNextNodeIdToInsert(id+1);
        PatriciaNode header = new PatriciaNode(id, key, skip, null, null, false, false);
        header.setLeftLink(header);
        header.setIsLeftAncestor(true);
        this.header = header;
    }

    private void setLookUpLogic(LookUpLogic lookUpLogic) { this.lookUpLogic = lookUpLogic; }
    private void initLookUpLogic() {
        this.lookUpLogic = new LookUpLogic(this);
    }

    private void setInsertLogic(InsertLogic insertLogic) { this.insertLogic = insertLogic; }
    private void initInsertLogic() {
        this.insertLogic = new InsertLogic(this);
    }

    private void setSearchLogic(SearchLogic searchLogic) { this.searchLogic = searchLogic; }
    private void initSearchLogic() { this.searchLogic = new SearchLogic(this); }

    private void setNextNodeIdToInsert(int nextNodeIdToInsert) {
        this.nextNodeIdToInsert = nextNodeIdToInsert;
    }
    protected void incrementNextNodeIdToInsert() {
        this.nextNodeIdToInsert++;
    }

    /** Normal methods **/
    /** Public methods **/

    // Algorithm P | Look-up
    public boolean isContainingPrefix(String searchWord) throws Exception {
        String binarySearchWordString = this.mixMachine.getBinaryString(searchWord);
        return this.lookUpLogic.isContainingPrefix(binarySearchWordString);
    }

    // Algorithm P + | Insert
    public void insertNextKeyIntoTree() throws Exception {
        int newKeyStartIndex = this.fileOps.getFileOpsStrategy().findNextWordStartIndex(this.insertLogic.getLatestInsertedNode().getKey());
        String wordNextToInsertFromFile = this.fileOps.getFileOpsStrategy().getWordStringFromFileStartingAtPosition(newKeyStartIndex);
        String binaryStringNextToInsertFromFile = this.mixMachine.getBinaryString(wordNextToInsertFromFile);
        this.insertLogic.insertNextKeyIntoTree(binaryStringNextToInsertFromFile, newKeyStartIndex);
    }

    // Algorithm P + | Search
    public PatriciaNode[] findNodesMatchingPrefix(String searchWord) throws Exception {
        String binarySearchWordString = this.mixMachine.getBinaryString(searchWord);
        PatriciaNode[] nodesMatchingPrefix = this.searchLogic.findNodesMatchingPrefix(binarySearchWordString);
        return nodesMatchingPrefix;
    }

    /** Protected (internal) methods **/

    protected String getNumberOfBitsFromFileAtPosition(int requestedAmountOfBits, int position) throws Exception {
        String charChain = this.fileOps.getFileOpsStrategy().getNumberOfCharsBasedOnNumberOfBitsFromFileAtPosition(requestedAmountOfBits, position);
        String binaryString = this.mixMachine.getBinaryString( charChain );
        binaryString = cutStringToLength(binaryString, requestedAmountOfBits);
        return binaryString;
    }

    protected String cutStringToLength(String string, int length) {
        if(string.length() > length) {
            string = string.substring(0, length);
        }
        return string;
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder()
                .append("PatriciaTree{\n")
                .append("\tfileOps = ").append(fileOps)
                .append(", \n\tmixMachine = ").append(mixMachine)
                .append(", \n\tlookUpLogic = ").append(lookUpLogic)
                .append(", \n\tinsertLogic = ").append(insertLogic)
                .append(", \n\tsearchLogic = ").append(searchLogic)
                .append(", \n\theader = ").append(header.toString(1))
                .append(", \n\tnextNodeIdToInsert = ").append(nextNodeIdToInsert)
                .append("\n}");
        return representation.toString();
    }
}
