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
    
    String file;

    public GetRequest(String File) {
        this.file = File;
    }

    public String getFile() {
        return file;
    }

    @Override
    //c'est le serveur qui recoit
    public void handle(ObjectOutputStream oos, Download download) {
        try{
            byte[] buffer = new byte[1000];
            int length = 0;
            
            File serverFile = new File(this.file);
            
            //on lit le fichier et on le met dans le buffer
            FileInputStream fis = new FileInputStream(serverFile);
            length = fis.read(buffer);
            System.out.println("Creation du get_reply");
            Message message = new GetReply("clientFile.odt", buffer, length);
//            int i=0;
//            while ((length = fis.read(buffer)) != -1){
//                Message message = new GetReply("clientFile.odt",buffer, length, i*1000);
//                System.out.println(i + " " + length + "offset: "+i*1000);
//                oos.writeObject(message);
//                i++;
//            }
            fis.close();
            //initialisation du fichier
//            File serverFile = new File("serverFile.odt");
//            serverFile.toPath();
            //remplissage du fichier
//            FileOutputStream fos = new FileOutputStream(serverFile);
//            fos.write(this.buffer,0,this.length);
//            fos.close();
            message.send(oos);
            System.out.println("envoie de get_reply");
        } catch (IOException ex) {
            Logger.getLogger(GetRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
