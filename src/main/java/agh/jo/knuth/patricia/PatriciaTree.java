package agh.jo.knuth.patricia;

import lombok.Getter;

@Getter
public class PatriciaTree {
    private PatriciaNode header;
    private FileOperations fileOperations;
    private int currentNodeId = 0;
    private MixMachine mixMachine;
    private AlgorithmP algorithmPLogic;

    /** Constructors **/

    private PatriciaTree() {}
    public PatriciaTree(String filePath, String fileName) throws Exception {
        this(filePath, fileName, Encoding.JAVA);
    }
    public PatriciaTree(String filePath, String fileName, Encoding encoding) throws Exception {
        setFileOperations(filePath, fileName);
        setMixMachine(encoding);
        setHeader();
        this.algorithmPLogic = new AlgorithmP(this);
    }
    public PatriciaTree(String filePath, String fileName, Encoding encoding, int amountOfBits) throws Exception {
        setFileOperations(filePath, fileName);
        setMixMachine(encoding, amountOfBits);
        setHeader();
        this.algorithmPLogic = new AlgorithmP(this);
    }

    /** Setters **/

    private void setFileOperations(String filePath, String fileName) {
        this.fileOperations = new FileOperations(filePath, fileName);
    }

    private void setMixMachine(Encoding encoding, int amountOfBits) {
        this.mixMachine = new MixMachine(encoding, amountOfBits);
    }

    private void setMixMachine(Encoding encoding) throws Exception {
        this.mixMachine = new MixMachine(encoding);
    }

    private void setHeader(PatriciaNode header) throws Exception { setHeader(); }
    private void setHeader() throws Exception {
        int key;
        if(this.fileOperations.isCharExistFromFileAtPosition(0)) key = 0;
        else throw new Exception("Could not create header. Character at position 0 did not exists.");
        int skip = 0;
        int id = this.currentNodeId;
        this.currentNodeId++;
        PatriciaNode header = new PatriciaNode(id, key, skip, null, null, false, false);
        header.setLeftLink(header);
        header.setIsLeftAncestor(true);
        this.header = header;
    }

    protected void incrementCurrentNodeId() {
        this.currentNodeId++;
    }

    /** Normal methods **/
    /** Public methods **/

    // Algorithm P | Look-up
    public boolean isContaining(String searchWord) throws Exception {
        String binarySearchWordString = this.mixMachine.getBinaryString(searchWord);
        return this.algorithmPLogic.isContaining(binarySearchWordString);
    }

    // Algorithm P + | Insert
    public void insertNextKeyIntoTree() throws Exception {
        int newKeyStartIndex = this.getFileOperations().findNextWordStartIndex(this.algorithmPLogic.getLatestInsertedNode().getKey());
        String wordNextToInsertFromFile = this.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        String binaryStringNextToInsertFromFile = this.mixMachine.getBinaryString(wordNextToInsertFromFile);
        this.algorithmPLogic.insertNextKeyIntoTree(binaryStringNextToInsertFromFile, newKeyStartIndex);
    }

    /** Protected (internal) methods **/

    protected String getNumberOfBitsFromFileAtPosition(int requestedAmountOfBits, int position) throws Exception {
        int amountOfBytes = (int) Math.ceil((float) requestedAmountOfBits / this.mixMachine.getAmountOfBits());
        String charChain = this.fileOperations.getNumberOfCharsFromFileAtPosition(amountOfBytes, position);
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
                .append("\tfileOperations = ").append(fileOperations)
                .append(", \n\tcurrentNodeId = ").append(currentNodeId)
                .append(", \n\tmixMachine = ").append(mixMachine)
                .append(", \n\talgorithmPLogic = ").append(algorithmPLogic)
                .append(", \n\theader = ").append(header.toString(1))
                .append("\n}");
        return representation.toString();
    }
}
