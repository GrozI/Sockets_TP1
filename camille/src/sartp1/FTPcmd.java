/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author gardellc
 */
public class FTPcmd implements Serializable{
    private String type; 
    private String filename;

    public FTPcmd(String type, String filename) {
        this.type = type;
        this.filename = filename;
    }
    
    public void send(OutputStream os){
        
    }
    
    public void read(InputStream is){
        
    }
    
}
