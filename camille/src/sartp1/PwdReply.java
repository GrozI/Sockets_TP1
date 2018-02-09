/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.ObjectOutputStream;

/**
 *
 * @author Camille Gardelle
 */
public class PwdReply extends Message{
    String path;

    public PwdReply(String path) {
        this.path = path;
    }
    
    @Override
    public void handle(ObjectOutputStream oos, State state) {
        System.out.println("Server:"+"\\"+path);
    }
    
}
