/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gardellc
 */
public class Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String hostname = "localhost";
        int port = 2000;
        Download download = new Download();
        boolean complete = false;
                
        try {
            SocketChannel socket = SocketChannel.open();
            socket.connect(new InetSocketAddress(hostname,port));
            
            System.out.println("connécté à "+hostname+" sur le port "+port);
            
            Message messageStart = new GetRequest("test.odt");
            
            ObjectOutputStream oos = new 
                              ObjectOutputStream(socket.socket().getOutputStream());
            ObjectInputStream ois = new 
                              ObjectInputStream(socket.socket().getInputStream());
//            File file = new File("test.odt");
            messageStart.send(oos);
            System.out.println("Envoie du get_request");
            
            Message message = (Message) ois.readObject();
            System.out.println("Lecture du get_reply");
            message.handle(oos, download);
            
            
            

//                case "GET_REQUEST":
//                    String response = isObject.readUTF();
//                    int result = is.read();
//                    
//                    System.out.println("réponse : "+response+" "+result);
//                    if(result == 0){
//                        FileOutputStream outstream = null;
//          
//                        String path = System.getProperty("user.dir");
//                        System.out.println("current dir = " + path);
//                        File outfile = new File(path+"C"+indice+message.filename);
//
//                        outstream = new FileOutputStream(outfile);
//
//                        while((length = is.read(buffer)) > 0)
//                          {
//                              outstream.write(buffer,0,length);
//                          }
//                        outstream.close();
//                        System.out.println("l'écriture du fichier a marché =)");
//                    }

//                case "PUT_REQUEST":
//                  //on cree un objet file pour le fichier que detient le client
//                  File infile = new File(message.filename);
//                  System.out.println(infile.exists());
//                  
//                  //on cree un flux qui permet de communiquer avec le fichier
//                  FileInputStream isFile = null;
//                  isFile = new FileInputStream(infile);
//                  
//                  while((length = isFile.read(buffer)) > 0)
//                  {
//        //            System.out.println("sfj");
//                      os.write(buffer,0,length);
//                  }
//                  
//                  isFile.close();
//                  
//                  System.out.println("l'envoi du fichier a marché =)");
//                    break;
//                default:
//                    System.out.println("la commande passée est inconnue");
//          }
//          os.close();
//          is.close();
//          
//          
//          
        } // end try
        catch (UnknownHostException ex) {
          System.err.println(ex);
        } catch (IOException ex) {
          System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
