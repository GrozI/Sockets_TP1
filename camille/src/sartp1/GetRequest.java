/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gardellc
 */
public class GetRequest extends Message{
    
    String fileName;

    public GetRequest(String file) {
        this.fileName = file;
    }

    @Override
    //c'est le serveur qui recoits
    public void handle(ObjectOutputStream oos, State state) {
        try{
            byte[] buffer = new byte[1000];
            int length = 0;
            int maxindex;
            
            File file = new File(System.getProperty("user.dir")+state.getAbstractPath()+"\\"+fileName);
            //on lit le fichier et on le met dans le buffer
            FileInputStream fis = new FileInputStream(file);
//            length = fis.read(buffer);
//            System.out.println("Creation du get_reply");
//            Message message = new GetReply("clientFile.odt", buffer, length);
            
            //initialisation du telechargement
            if (file.length()%1000 != 0){
                maxindex = (int) (file.length()/1000 + 1);
            }else{
                maxindex = (int) (file.length()/1000);
            }
            
            int i=1;
            while ((length = fis.read(buffer)) != -1){
                Message message = new GetReply("clientFile.odt", buffer, length, i, maxindex);
                System.out.println(i + " " + length );
                message.send(oos);
                i++;
            }
            fis.close();
            
            
            //initialisation du fichier
//            File serverFile = new File("serverFile.odt");
//            serverFile.toPath();
            //remplissage du fichier
//            FileOutputStream fos = new FileOutputStream(serverFile);
//            fos.write(this.buffer,0,this.length);
//            fos.close();
            System.out.println("l'envoie des get_reply est terminé");
        } catch (IOException ex) {
            Logger.getLogger(GetRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
