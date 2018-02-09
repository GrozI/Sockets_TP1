/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class FTPClient {

    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) throws ClassNotFoundException {
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String path = System.getProperty("user.dir");
        String abstractPath = "C:";
        State state = new State();
        Boolean connected = false;
        
        
        SocketChannel channel = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        
        // we break out with <control><C> 
        while (true) {

            // read what the user entered
            System.out.print(abstractPath + ">");
            try {
                commandLine = console.readLine();
                String delims = "[ ]+";
                String[] tokens = commandLine.split(delims);

                if (connected == false){
                    if(tokens[0].equals("connect")){
                        if (tokens.length == 3){
                            channel = SocketChannel.open();
                            try{
                                channel.connect(new InetSocketAddress(tokens[1], 2000 ));
                                System.out.println("connected");
                                connected = true;
                                abstractPath = "Server:";
                            }catch (IOException ex) {
                                System.out.println("les paramètres sont incorrects");
                            }
                            oos = new ObjectOutputStream(channel.socket().getOutputStream());
                            ois = new ObjectInputStream(channel.socket().getInputStream());
                        }else{
                            System.out.println("la commande connect prend deux arguments");
                        }
                    }
                }
                
                
                if(connected == true){
                    
                    if (tokens[0].equals("cd")){
                        if (tokens.length == 2){
                            Message m = new CdRequest(tokens[1]);
                            m.send(oos);
                            Message mResponse = (Message) ois.readObject();
                            mResponse.handle(oos, state);
                            abstractPath = "Server:"+state.getAbstractPath();
                        }
                        else{
                            System.out.println("you need to put arguments");
                        }
                    }
                    
                    if(tokens[0].equals("pwd")){
                        if (tokens.length == 1){
                            Message m = new PwdRequest();
                            m.send(oos);
                            Message mResponse = (Message) ois.readObject();
                            mResponse.handle(oos, state);
                        }
                        else{
                            System.out.println("pwd ne prend pas d'arguments");
                        }
                    }

                    if (tokens[0].equals("ls")){
                       if (tokens.length == 1){
                            Message m = new LsRequest();
                            m.send(oos);
                            Message mResponse = (Message) ois.readObject();
                            mResponse.handle(oos, state);
                       }else{
                           System.out.println("ls ne prend pas d'arguments");
                       }
                    }
                    
                    if (tokens[0].equals("get")){
                       if (tokens.length == 2){
                            Message m = new GetRequest(tokens[1]);
//                              Message m = new GetRequest("mesureInvariante.pdf");
                            m.send(oos);
                            state.setDownloading(true);
                            while (state.isDownloading()){
                                Message mResponse = (Message) ois.readObject();
                                mResponse.handle(oos, state);
                            }
                       }else{
                           System.out.println("get prend un seul argument pour le moment");
                       }
                    }
                    
                }

            } catch (IOException ex) {
                System.out.println("petit problem");
            }


      // if the user entered a return, just loop again
   //   if (commandLine.equals(""))
   //    continue;

      /** The steps are:
      (1) parse the input to obtain the command and

         any parameters
      (2) create a ProcessBuilder object
      (3) start the process
      (4) obtain the output stream
      (5) output the contents returned by the command */
        }
    }
    
}
