/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Camille Gardelle
 */
public class State {
    private String abstractPath = "";
    
    private byte[][] fileBytes = null;
    private String fileName;
    private int indexMax;
    private int lastLenght;
    private boolean downloading = false;


    public State() {
    }

    public String getAbstractPath() {
        return abstractPath;
    }

    public void setAbstractPath(String abstractPath) {
        this.abstractPath = abstractPath;
    }
    
    public void cd(String repertory){
        if (abstractPath ==  ""){
            abstractPath = repertory;
        }else{
            abstractPath = abstractPath + "\\"+ repertory;
        }
    }
    
    public void initialiseFile(String file, byte[] buffer, int length, int maxindex){
        fileBytes = new byte[maxindex][length];
        indexMax = maxindex;
    }
    
    public void populate(String file, byte[] buffer, int length, int index, int maxindex) throws FileNotFoundException, IOException{
        int j;
        if (index == 1){
            initialiseFile(file, buffer, length, maxindex);
        }
        if (index != indexMax){
            for(j=0;j<1000;j++){
                this.fileBytes[index-1][j]= buffer[j];
                System.out.print(buffer[j]);
            }
            System.out.println("");
            
        }else{
             //initialisation du fichier
            File clientFile = new File(file);
            if (clientFile.exists()){
                j=1;
                while ((clientFile = new File(file+j)).exists()){
                    j++;
                }
            }
            
            System.out.println("Creation du fichier");
            FileOutputStream fop = new FileOutputStream(clientFile);
            
            for (int i=0;i<maxindex-1;i++){
                System.out.println(i);
                for(j=0;j<1000;j++){
                    System.out.print(fileBytes[i][j]);
                }
                System.out.println("");
                fop.write(fileBytes[i]);
                
            }
            fop.write(buffer,0,length);
            for(j=0;j<length;j++){
                System.out.print(buffer[j]);
            }
            System.out.println("dernier");
            fop.flush();
            fop.close();
            this.setDownloading(false);
            System.out.println("Fichier rempli");

        }
        
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}

