package agh.jo.utils.file;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Getter
@Setter
public class FileReadContainer {
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;

    public FileReadContainer(String filePath, String fileName) {
        this.file = FilesOps.openExistingFile(filePath, fileName);
        this.fileReader = FilesOps.openFileReader(file);
        this.bufferedReader = FilesOps.openBufferedReader(fileReader);
    }

    public void close() {
        this.bufferedReader = FilesOps.closeBufferedReader(this.bufferedReader);
        this.fileReader = FilesOps.closeFileReader(this.fileReader);
    }
}
