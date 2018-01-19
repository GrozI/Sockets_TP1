/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author gardellc
 */
public class Get extends Message{

    public Get(String filename) {
        this.name = "GET";
        this.filename = filename;
    }

    @Override
    public void send(OutputStream os) {
        
    }

    @Override
    public void receive(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
