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
    
    String file; //contient le nom du fichier ou le message d'erreur
    byte[] buffer;
    int length;
    int index;
    int maxindex;
    boolean ok;
    
    public GetReply(boolean ok, String file, byte[] buffer, int length, int index, int maxindex) {
        int j;
        this.ok = ok;
        this.file = file;
        this.buffer = buffer;
        for (j=0;j<1000;j++){
            System.out.print(buffer[j]);
        }
        System.out.println("");
        this.length = length;
        this.index = index;
        this.maxindex = maxindex;
    }
    
    @Override
    public void handle(ObjectOutputStream oos, State state) {
        if (ok){
            try {
                state.populate(file, buffer, length, index, maxindex);
            } catch (IOException ex) {
                Logger.getLogger(GetReply.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println(file);
            state.setDownloading(false);
        }
        
        
    }
    
    
}
