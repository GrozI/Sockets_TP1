/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.File;
import java.io.FileInputStream;
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
public class GetRequest extends Message{
    
    String File;
    byte[] buffer = new byte[1024];
    int length;

    public GetRequest(String File) {
        this.File = File;
    }

    public String getFile() {
        return File;
    }

    @Override
    //c'est le client qui envoie
    public void send(ObjectOutputStream oos) {
        //initialisation du buffer et du fichier
        try{
            File clientFile = new File("clientFile.odt");
            clientFile.toPath();
            FileOutputStream fos = new FileOutputStream(clientFile);
            fos.write(57);
            fos.close();
            
            //on lit l'entier et on le met dans le buffer
            FileInputStream fis = new FileInputStream(clientFile);
            this.length = fis.read(this.buffer);
//            oos.writeObject(buffer);
            fis.close();
            
            //envoie le message
            oos.writeObject(this);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GetRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    //c'est le serveur qui recoit
    public void receive(ObjectInputStream ois) {
        try{
            //initialisation du fichier
            File serverFile = new File("serverFile.odt");
            serverFile.toPath();
            //remplissage du fichier
            FileOutputStream fos = new FileOutputStream(serverFile);
            fos.write(this.buffer,0,this.length);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(GetRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void read() {
        System.out.println("Message: " + this.getClass().getName() + "  " + this.getFile());
    }
    
}
