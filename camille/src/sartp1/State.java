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
            }
            
        }else{
             //initialisation du fichier
            File clientFile = new File(fileName);
            if (clientFile.exists()){
                j=1;
                while ((clientFile = new File(j+fileName)).exists()){
                    j++;
                }
            }
            
//            System.out.println("Creation du fichier");
            FileOutputStream fos = new FileOutputStream(clientFile);
            
            for (int i=0;i<maxindex-1;i++){
                fos.write(fileBytes[i]);
            }
            fos.write(buffer,0,length);
            fos.flush();
            fos.close();
            this.setDownloading(false);
            System.out.println("Le téléchargement est terminé");

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

