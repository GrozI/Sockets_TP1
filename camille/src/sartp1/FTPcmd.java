/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author gardellc
 */
public abstract class FTPcmd {
    
    String name;
    
    public abstract void send(OutputStream outStream);
    public abstract void receive(InputStream inStream);
}
