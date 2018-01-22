/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author gardellc
 */
public abstract class Message implements Serializable{
    
    public abstract void send(OutputStream os);
    
    public abstract void receive(InputStream is);
}
