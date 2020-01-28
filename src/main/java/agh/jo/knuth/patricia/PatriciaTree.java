package agh.jo.knuth.patricia;

import agh.jo.knuth.patricia.file.ops.FileOps;
import agh.jo.knuth.patricia.file.ops.FileOpsStrategy;
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

    /** Constructors **/

    private PatriciaTree() throws NotForUseException { throw new NotForUseException(this.getClass().getName(), "PatriciaTree()", "Not for use, implementation not possible."); }
    public PatriciaTree(String filePath, String fileName) throws Exception {
        this(filePath, fileName, Encoding.JAVA, WordStrategy.START_POSITION_TO_EOF);
    }
    public PatriciaTree(String filePath, String fileName, Encoding encoding) throws Exception {
        setFileOps(filePath, fileName);
        setMixMachine(encoding);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }
    public PatriciaTree(String filePath, String fileName, Encoding encoding, int amountOfBits) throws Exception {
        setFileOps(filePath, fileName);
        setMixMachine(encoding, amountOfBits);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }
    public PatriciaTree(String filePath, String fileName, Encoding encoding, WordStrategy wordStrategy) throws Exception {
        setFileOps(filePath, fileName, wordStrategy);
        setMixMachine(encoding);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }
    public PatriciaTree(String filePath, String fileName, Encoding encoding, int amountOfBits, WordStrategy wordStrategy) throws Exception {
        setFileOps(filePath, fileName, wordStrategy);
        setMixMachine(encoding, amountOfBits);
        setNextNodeIdToInsert(0);
        setHeader();
        initLookUpLogic();
        initInsertLogic();
        initSearchLogic();
    }

    /** Setters **/

    private void setFileOps(String filePath, String fileName) throws Exception {
        this.fileOps = new FileOps(filePath, fileName);
    }

    private void setFileOps(String filePath, String fileName, WordStrategy wordStrategy) throws Exception {
        this.fileOps = new FileOps(filePath, fileName, wordStrategy);
    }

    private void setMixMachine(Encoding encoding, int amountOfBits) { this.mixMachine = new MixMachine(encoding, amountOfBits); }
    private void setMixMachine(Encoding encoding) throws Exception { this.mixMachine = new MixMachine(encoding); }

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
    public boolean isContaining(String searchWord) throws Exception {
        String binarySearchWordString = this.mixMachine.getBinaryString(searchWord);
        return this.lookUpLogic.isContaining(binarySearchWordString);
    }

    // Algorithm P + | Insert
    public void insertNextKeyIntoTree() throws Exception {
        int newKeyStartIndex = this.fileOps.getFileOpsStrategy().findNextWordStartIndex(this.insertLogic.getLatestInsertedNode().getKey());
        String wordNextToInsertFromFile = this.fileOps.getFileOpsStrategy().getWordStringFromFileStartingAtPosition(newKeyStartIndex);
        String binaryStringNextToInsertFromFile = this.mixMachine.getBinaryString(wordNextToInsertFromFile);
        this.insertLogic.insertNextKeyIntoTree(binaryStringNextToInsertFromFile, newKeyStartIndex);
    }

    // Algorithm P + | Search
    public PatriciaNode[] findNodesMatching(String searchWord) throws Exception {
        String binarySearchWordString = this.mixMachine.getBinaryString(searchWord);
        PatriciaNode[] matchingNodes = this.searchLogic.findNodesMatching(binarySearchWordString);
        return matchingNodes;
    }

    /** Protected (internal) methods **/

    protected String getNumberOfBitsFromFileAtPosition(int requestedAmountOfBits, int position) throws Exception {
        int amountOfBytes = (int) Math.ceil((float) requestedAmountOfBits / this.mixMachine.getAmountOfBits());
        String charChain = this.fileOps.getFileOpsStrategy().getNumberOfCharsFromFileAtPosition(amountOfBytes, position);
        String binaryString = this.mixMachine.getBinaryString( charChain );
        binaryString = cutStringToLength(binaryString, requestedAmountOfBits);
        return binaryString;
    }

    protected String cutStringToLength(String string, int length) {
        if(string.length() > length) {
            StringBuilder binaryStringWithoutExcessiveBits = new StringBuilder();
            for (int i = 0; i < length; i++) {
                binaryStringWithoutExcessiveBits.append(string.charAt(i));
            }
            string = binaryStringWithoutExcessiveBits.toString();
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
