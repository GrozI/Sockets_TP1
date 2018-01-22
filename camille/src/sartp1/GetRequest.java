/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author gardellc
 */
public class GetRequest extends Message{
    
    String File;

    public GetRequest(String File) {
        this.File = File;
    }

    public String getFile() {
        return File;
    }

    @Override
    //c'est le client qui envoie
    public void send(OutputStream os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    //c'est le serveur qui recoit
    public void receive(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read() {
        System.out.println("Message: " + this.getClass().getName() + "  " + this.getFile());
    }
    
}
