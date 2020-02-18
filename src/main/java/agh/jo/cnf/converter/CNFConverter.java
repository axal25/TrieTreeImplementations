package agh.jo.cnf.converter;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;

@Setter
@Getter
public class CNFConverter {
    private CNFReader cnfReader;
    private CNFWriter cnfWriter;
    private char inputCNFdelimiterReplacement;
    public static char INPUT_DELIMITER = ' ';
    public CNFConverter(String inFilePath, String inFileName, String outFilePath, String outFileName, char EOCNF, char inputCNFdelimiterReplacement, char outputEOF) {
        this.cnfReader = new CNFReader(inFilePath, inFileName);
        this.cnfWriter = new CNFWriter(outFilePath, outFileName, EOCNF, outputEOF);
        this.inputCNFdelimiterReplacement = inputCNFdelimiterReplacement;
    }

    public void convert() throws Exception {
        String line = cnfReader.readCNF();
        while(line!=null && !line.isEmpty()) {
            line = readCNFToWriteCNF(line);
            cnfWriter.writeCNF(line);
            line = cnfReader.readCNF();
        }
        cnfReader.close();
        cnfWriter.close();
    }

    public String readCNFToWriteCNF(String cnf) {
        Long[] longCNF = parseCNF(cnf);
        Arrays.sort(longCNF);
        return literalArrayToString(longCNF);
    }

    public Long[] parseCNF(String cnf) {
        LinkedList<Long> literals = new LinkedList<>();
        LinkedList<Integer> delimiterIndexes = new LinkedList<>();
        for (int i = 0; i < cnf.length(); i++) {
            if(cnf.charAt(i) == INPUT_DELIMITER) delimiterIndexes.add(i);
        }
        int prevDelimiterIndex = 0;
        for (int delimiterIndex:delimiterIndexes) {
            literals.add(Long.parseLong(cnf.substring(prevDelimiterIndex, delimiterIndex)));
            prevDelimiterIndex = delimiterIndex+1;
        }
        return (Long[]) literals.toArray(new Long[literals.size()]);
    }

    public String literalArrayToString(Long[] literalArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<literalArray.length; i++) {
            if(i!=0) stringBuilder.append(this.inputCNFdelimiterReplacement);
            stringBuilder.append(literalArray[i]);
        }
        return stringBuilder.append(this.inputCNFdelimiterReplacement).append('0').toString();
    }
}
