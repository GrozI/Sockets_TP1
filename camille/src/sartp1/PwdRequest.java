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
public class PwdRequest extends Message{

    public PwdRequest() {
    }

    @Override
    public void handle(ObjectOutputStream oos, State state) {
        Message m = new PwdReply(state.getAbstractPath());
        m.send(oos);
    }
    
}
