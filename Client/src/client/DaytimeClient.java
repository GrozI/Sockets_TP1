package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DaytimeClient {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Erreur, nombre d'arguments incorrect !");
            System.exit(0);
        }
        String hostname = args[0];
        String port = args[1];
        String request = args[2];
        String filename = args[3];
        try {
            Socket theSocket = new Socket(hostname, Integer.parseInt(port));
            InputStream timeStream = theSocket.getInputStream();
            StringBuffer time = new StringBuffer();
            //String hostname = "localhost";
            switch (request) {
                case "GET_REQUEST":
                    int c;
                    while ((c = timeStream.read()) != -1) {
                        time.append((char) c);
                    }
                    String timeString = time.toString().trim();
                    System.out.println("It is " + timeString + " at " + hostname);

                case "PUT_REQUEST": {
                }
                ;
            }
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

}

