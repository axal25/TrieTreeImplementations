package agh.jo.cnf.converter;

import agh.jo.utils.file.RandomAccessFileContainer;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class CNFWriter {
    private String outFilePath;
    private String outFileName;
    private char EOCNF;
    private char outputEOF;
    private RandomAccessFileContainer randomAccessFileContainer = null;

    protected CNFWriter(String outFilePath, String outFileName, char EOCNF, char outputEOF) {
        setOutFilePath(outFilePath);
        this.outFileName = outFileName;
        this.EOCNF = EOCNF;
        this.outputEOF = outputEOF;
    }

    private void setOutFilePath(String outFilePath) {
        this.outFilePath = System.getProperty("user.dir") + outFilePath;
    }

    public void writeCNF(String inputCNF) throws IOException {
        if(randomAccessFileContainer == null) {
            randomAccessFileContainer = new RandomAccessFileContainer(this.outFilePath, this.outFileName);
            randomAccessFileContainer.seek(0);
        }
        randomAccessFileContainer.getRandomAccessFile().write(
                new StringBuilder().append(inputCNF).append(this.EOCNF).toString().getBytes(StandardCharsets.UTF_8)
        );
    }

    public void close() {
        try {
            randomAccessFileContainer.getRandomAccessFile().write(
                    new StringBuilder().append(outputEOF).toString().getBytes(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(randomAccessFileContainer!=null) {
            randomAccessFileContainer.close();
            randomAccessFileContainer = null;
        }
    }
}
