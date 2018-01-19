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

/**
 *
 * @author gardellc
 */
public abstract class Message {
    protected String name;
    protected String filename;

    public Message() {
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }
    
    
    
    public void send(OutputStream os) throws IOException{
        DataOutputStream osObject = new DataOutputStream(os);
        osObject.writeUTF(this.name);
        osObject.writeUTF(this.filename);
    };
    
    public abstract void receive(InputStream is);
}
