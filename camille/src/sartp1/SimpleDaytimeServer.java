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
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * A simple TCP server for the daytime service. See RFC867 for details. This
 * implementation lacks meaningful exception handling and cannot handle UDP
 * connections.
 */
public class SimpleDaytimeServer {
  public static void main(String args[]) throws java.io.IOException {
    // RFC867 specifies port 13 for this service. On Unix platforms,
    // you need to be running as root to use that port, so we allow
    // this service to use other ports for testing.
    int port = 2000; //au dessus de 1024 sur linux
    if (args.length > 0)
      port = Integer.parseInt(args[0]);
    
    String repertory = System.getProperty("user.dir")+"/serverFiles";
    System.out.println(repertory);
    
    // Create a channel to listen for connections on.
    ServerSocketChannel server = ServerSocketChannel.open();
    
    // Bind the channel to a local port. Note that we do this by obtaining
    // the underlying java.net.ServerSocket and binding that socket.
    server.socket().bind(new InetSocketAddress(port));
    System.out.println(server.toString());
    
    // Get an encoder for converting strings to bytes
    CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();
    //nombre de connexion faites sur la socket ouverte cote serveur
    int numDownload = 0;
    
    for (;;) { // Loop forever, processing client connections
      // Wait for a client to connect
      SocketChannel client = server.accept();
      numDownload++;
      System.out.println("pour la "+numDownload+"e fois");
      
      try 
      {
          //on recupere le flux d'envoie
          OutputStream os = client.socket().getOutputStream();
          
          //on ecrit dans le flux d'envoie le numero du fichier
          os.write(numDownload);
      
          //on cree un objet file pour le fichier que detient le serveur
          File infile = new File("test.odt");
          System.out.println(infile.exists());
          
          //on cree un flux qui permet de communiquer avec le fichier
          FileInputStream instream = null;
          instream = new FileInputStream(infile);
          
          //on cree un buffer
          byte[] buffer = new byte[1024];
          
          int length;
          
          while((length = instream.read(buffer)) > 0)
          {
//              System.out.println("sfj");
              os.write(buffer,0,length);
          }
          
          instream.close();
          os.close();
          
          System.out.println("l'envoi du fichier a marché =)");
          
      }
      catch(IOException ioe)
      {
          ioe.printStackTrace();
      }
      // Build response string, wrap, and encode to bytes
      
      //String date = new java.util.Date().toString() + "\r\n";
      //ByteBuffer response = encoder.encode(CharBuffer.wrap(date));
      //System.out.println("response"+response.toString());
      
      //byte[] byteArray = {56,58};
      //System.out.println(byteArray[0] +" "+byteArray[1]);
      //ByteBuffer response = ByteBuffer.wrap(byteArray);
      
      //File data = new File("test.odt");
      //byte[] byteArray = BytesUtil.toByteArray(data);
      //ByteBuffer response = ByteBuffer.wrap(byteArray);
      
      // Send the response to the client and disconnect.
      //client.write(leng);
      client.close();
    }
  }
}
