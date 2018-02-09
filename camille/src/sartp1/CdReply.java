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
public class CdReply extends Message{
    boolean directoryChanged;
    String abstractPath;

    public CdReply(boolean directoryChanged, String abstractPath) {
        this.directoryChanged = directoryChanged;
        this.abstractPath = abstractPath;
    }

    @Override
    public void handle(ObjectOutputStream oos, State state) {
        if(directoryChanged == true){
            System.out.println("path change");
            state.setAbstractPath(abstractPath);
        }
        else{
            System.out.println(abstractPath);
        }
    }
}

