package agh.jo.utils.file;

import agh.jo.knuth.patricia.PatriciaTree;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@Getter
@Setter
public class RandomAccessReadContainer {
    File file;
    RandomAccessFile randomAccessFile;

    public RandomAccessReadContainer(String filePath, String fileName) {
        final String functionName = "RandomAccessReadContainer(String filePath, String fileName)";
        this.file = FilesOps.openExistingFile(filePath, fileName);
        try {
            this.randomAccessFile = new RandomAccessFile(this.file, "rw");
        } catch (FileNotFoundException e) {
            this.randomAccessFile = null;
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
        }
    }

    public void close() {
        final String functionName = "void close()";
        try {
            this.randomAccessFile.close();
        } catch (IOException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
        }
    }
}
