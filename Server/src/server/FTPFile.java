/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.*;
import java.io.*;
/**
 *
 * @author grozir
 */
public class FTPFile {
  String filename = "";
  File data;
  public FTPFile(String name){
      this.filename = name;
      if (this.exists()) this.getData();
  }
  public boolean exists()
  {
      java.io.File f = new java.io.File("./" + filename);
      boolean  a = f.exists();

      if(a)
      {
          System.out.println("ça va, ca existe");
      }
      else
      {
          System.out.println("NOOOOOOOOOOOOOOOOOOOOON");
      }
      return a;
  }
  public void getData()
  {
      if (this.exists())
      {
          this.data= new File(filename);
          System.out.println("Le fichier est bien chargé");
      }
      else
      {
          System.out.println("Yapadfichier");
      }
  }
  public void writeFile()
  {
      FileInputStream instream = null;
      FileOutputStream outstream = null;
      try 
      {
          Path currentRelativePath = Paths.get("");
          String s = currentRelativePath.toAbsolutePath().toString();
          
          File infile = this.data;
          File outfile = new File(s+"/"+filename);
          
          instream = new FileInputStream(infile);
          outstream = new FileOutputStream(outfile);
          
          byte[] buffer = new byte[1024];
          
          int length;
          
          while((length = instream.read(buffer)) > 0)
          {
              outstream.write(buffer,0,length);
          }
          instream.close();
          outstream.close();
          
          System.out.println("l'écriture du fichier a marché =)");
          
      }
      catch(IOException ioe)
      {
          ioe.printStackTrace();
      }
  }
}
