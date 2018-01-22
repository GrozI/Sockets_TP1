/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gardellc
 */
public class GetReply extends Message{
    
    String file;
    byte[] buffer;
    int length;
    int index;
    int maxindex;
    
    
    public GetReply(String file, byte[] buffer, int length, int index, int maxindex) {
        this.file = file;
        this.buffer = buffer;
        this.length = length;
        this.index = index;
        this.maxindex = maxindex;
    }
    
    @Override
    public void handle(ObjectOutputStream oos, Download download) {
            
        try {
            //initialisation du fichier
            File serverFile = new File(file);
            System.out.println("Creation du fichier");
            
            if(download.getFileBytes() == null){
                download.setFileBytes(new byte[maxindex][1000]);
                download.setFileName(file);
            }
            
            //remplissage du fichier
//            FileOutputStream fos = new FileOutputStream(serverFile);
//            fos.write(buffer,0,length);
//            fos.close();
            download.addPartOfFile(index-1, buffer);
            
            if (index == maxindex){
                download.setLastLenght(length);
            }
            
            boolean test = true;
            for (int i=0;i<maxindex;i++){
                test = test && (download.getFileBytes()[i] != null);
            }
            
            if(test == true){
                
            }
            
            System.out.println("Fichier rempli");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GetReply.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetReply.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
