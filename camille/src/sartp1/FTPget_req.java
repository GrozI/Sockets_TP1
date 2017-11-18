/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

/**
 *
 * @author gardellc
 */
public class FTPget_req extends FTPcmd{
    
    String file;
    
    @Override
    public void send(OutputStream outStream){
        try {
        /////////////Envoie de la commande et du nom de fichier//////////////
          DataOutputStream osObject = new DataOutputStream(outStream);
          osObject.writeUTF(name);
          osObject.writeUTF(file);
        } // end try
        catch (IOException ex) {
          System.err.println(ex);
        } 
    }
    @Override
    public void receive(InputStream inStream){
        try {
        //////////Reception de la commande et du nom de fichier////////////
            DataInputStream isObject = new DataInputStream(inStream);
            String name = isObject.readUTF();
            String file = isObject.readUTF();
        } // end try
        catch (IOException ex) {
          System.err.println(ex);
        } 
        
    }
}
