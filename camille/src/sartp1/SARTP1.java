/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author gardellc
 */
public class SARTP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String hostname = "localhost";
        int port = 2000;
        
        try {

          Socket theSocket = new Socket(hostname, port );
          System.out.println("connécté à "+hostname+" sur le port "+port);
          
          InputStream is = theSocket.getInputStream();
          OutputStream os = theSocket.getOutputStream();
          
          String cmd = "GET_REQUEST";
          String filename = "test.odt";
          
          
          /////////////Envoie de la commande et du nom de fichier//////////////
          DataOutputStream osObject = new DataOutputStream(os);
          osObject.writeUTF(cmd);
          osObject.writeUTF(filename);
          
          DataInputStream isObject = new DataInputStream(is);
          
          int indice = is.read();
          
          System.out.println("le Client envoie un "+cmd+" de "+filename);
          
          byte[] buffer = new byte[1024];
          int length;
          
          switch (cmd){
                case "GET_REQUEST":
                    String response = isObject.readUTF();
                    int result = is.read();
                    
                    System.out.println("réponse : "+response+" "+result);
                    if(result == 0){
                        FileOutputStream outstream = null;
          
                        String path = System.getProperty("user.dir");
              //          System.out.println("current dir = " + path);
                        File outfile = new File(path+"C"+indice+filename);

                        outstream = new FileOutputStream(outfile);

                        while((length = is.read(buffer)) > 0)
                          {
                              outstream.write(buffer,0,length);
                          }
                        outstream.close();
                        System.out.println("l'écriture du fichier a marché =)");
                    }
                    
                    break;
                case "PUT_REQUEST":
                  //on cree un objet file pour le fichier que detient le client
                  File infile = new File(filename);
                  System.out.println(infile.exists());
                  
                  //on cree un flux qui permet de communiquer avec le fichier
                  FileInputStream isFile = null;
                  isFile = new FileInputStream(infile);
                  
                  while((length = isFile.read(buffer)) > 0)
                  {
        //            System.out.println("sfj");
                      os.write(buffer,0,length);
                  }
                  
                  isFile.close();
                  
                  System.out.println("l'envoi du fichier a marché =)");
                    break;
                default:
                    System.out.println("la commande passée est inconnue");
          }
          os.close();
          is.close();
          
          
          
        } // end try
        catch (UnknownHostException ex) {
          System.err.println(ex);
        } catch (IOException ex) {
          System.err.println(ex);
        } 
        
        
    }
    
}
