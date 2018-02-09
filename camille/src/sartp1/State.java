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
        fileName = file;
        indexMax = maxindex;
    }
    
    public void populate(String file, byte[] buffer, int length, int index, int maxindex) throws FileNotFoundException, IOException{
        if (index == 1){
            initialiseFile(file, buffer, length, maxindex);
        }
        if (index != indexMax){
            this.fileBytes[index-1]= buffer;
        }else{
             //initialisation du fichier
            File clientFile = new File(file);
            System.out.println("Creation du fichier");
            FileOutputStream fop = new FileOutputStream(clientFile);
            
            for (int i=0;i<maxindex;i++){
                fop.write(fileBytes[i]);
            }
            fop.write(buffer,0,length);
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
    
    
}

