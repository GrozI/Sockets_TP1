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
public class LsReply extends Message{
    String childs[];

    public LsReply(String[] childs) {
        this.childs = childs;
    }
    
    @Override
    public void handle(ObjectOutputStream oos, State state) {
        if (childs != null){
            for(String child: childs){
                System.out.println(child);
            }
        }
    }
    
}
