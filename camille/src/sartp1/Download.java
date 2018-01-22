/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

/**
 *
 * @author gardellc
 */
public class Download {
    private byte[][] fileBytes = null;
    private String fileName;
    private int lastLenght;

    public Download() {
    }

    public byte[][] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[][] fileBytes) {
        this.fileBytes = fileBytes;
    }
    
    public void addPartOfFile(int i, byte[] tab){
        this.fileBytes[i] = tab;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLastLenght() {
        return lastLenght;
    }

    public void setLastLenght(int lastLenght) {
        this.lastLenght = lastLenght;
    }
    
    
    
}
