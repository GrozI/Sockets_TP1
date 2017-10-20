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
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

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
        String repertory = System.getProperty("user.dir")+"/clientFiles";
        
        try {
          Socket theSocket = new Socket(hostname, port );
          System.out.println("connécté à "+hostname+" sur le port "+port);
          
          InputStream is = theSocket.getInputStream();
          
      
          //ByteBuffer.wrap(bytes).getlong()
          
          
          
          /*int b;
          byte[] table = new byte[1];
          byte[] table2 = new byte[1];
          timeStream.read(table);
          timeStream.read(table2);
          //while ((b = timeStream.read()) != -1){
              //System.out.println("aaa");
          //}
          System.out.println(table[0]);
          System.out.println(table2[0]);
          */
          
          //InputStream timeStream = theSocket.getInputStream();
          //StringBuffer time = new StringBuffer();
          //int c;
          //while ((c = timeStream.read()) != -1)
            //time.append((char) c);
          //String timeString = time.toString().trim();
          //System.out.println("It is " + timeString + " at " + hostname);
          //System.out.println(timeString);
          
          
          
        } // end try
        catch (UnknownHostException ex) {
          System.err.println(ex);
        } catch (IOException ex) {
          System.err.println(ex);
        }
        
        
    }
    
}
