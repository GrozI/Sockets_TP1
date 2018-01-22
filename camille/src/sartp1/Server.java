/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

/*
 * Copyright (c) 2004 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 3nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose,
 * including teaching and use in open-source projects.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book, 
 * please visit http://www.davidflanagan.com/javaexamples3.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A simple TCP server for the daytime service. See RFC867 for details. This
 * implementation lacks meaningful exception handling and cannot handle UDP
 * connections.
 */
public class Server {
    public static void main(String args[]){
        Download download = new Download();
        int port = 2000; //au dessus de 1024 sur linux
    //    if (args.length > 0){
    //        port = Integer.parseInt(args[0]);
    //    }

        String repertory = System.getProperty("user.dir");
        System.out.println("current dir: "+repertory);

        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(port));
            
            for (;;) {
                SocketChannel client = server.accept();
                System.out.println("Connexion ouverte");

                ObjectInputStream ois = 
                         new ObjectInputStream(client.socket().getInputStream());
                ObjectOutputStream oos = new 
                              ObjectOutputStream(client.socket().getOutputStream());
//                File file = (File) ois.readObject();
                
                Message message = (Message) ois.readObject();
                System.out.println("reception du get_request");
                message.handle(oos, download);
                
                
            }
            
            

//            //on cree un buffer
//            byte[] buffer = new byte[1024];
//            int length;
    //                case "GET_REQUEST":
    ////                    System.out.println("c'est bon!");
    //                    //on cree un objet file pour le fichier que detient le serveur
    //                    File infile = new File(file);
    //                    System.out.println(infile.exists());
    //
    //                    if (infile.exists()){
    //                        FileInputStream instream = null;
    //                        instream = new FileInputStream(infile);
    //
    //                        while((length = instream.read(buffer)) > 0)
    //                        {
    //            //              System.out.println("sfj");
    //                            os.write(buffer,0,length);
    //                        }
    //
    //                        instream.close();
    //                        osObject.writeUTF("GET_REPLY");
    //                        os.write(0);
    //                    } else {
    //                        osObject.writeUTF("GET_REPLY");
    //                        os.write(1);
    //                    }
    //                    //on cree un flux qui permet de communiquer avec le fichier
    //                    break;
    //                case "PUT_REQUEST":
    //                    FileOutputStream outstream = null;
    //          
    //                    String path = System.getProperty("user.dir");
    //          //        System.out.println("current dir = " + path);
    //                    File outfile = new File(path+"S"+numConnect+file);
    //                    System.out.println("le fichier existe : "+outfile.exists());
    //                    if (outfile.exists()){
    //                        int i=1;
    //                        //outfile =  new File(path)
    //                    }
    //                    
    //                    outstream = new FileOutputStream(outfile);
    //
    //                    while((length = is.read(buffer)) > 0)
    //                      {
    //                          outstream.write(buffer,0,length);
    //                      }
    //                    
    //                    outstream.close();
    //                    break;
    //                default:
    //                    System.out.println("la commande passée est inconnue");
    //            }

    //        }
    //        catch(IOException ioe)
    //        {
    //            ioe.printStackTrace();
    //        }
    //        
    //        client.close();
    //        System.out.println("serveur fermé");
    //    }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
