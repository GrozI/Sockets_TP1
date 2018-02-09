/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sartp1;

import java.io.File;
import java.io.ObjectOutputStream;

/**
 *
 * @author Camille Gardelle
 */
public class LsRequest extends Message{

    public LsRequest() {
    }
    
    @Override
    public void handle(ObjectOutputStream oos, State state) {
        String way;
        if (state.getAbstractPath() == ""){
            way = System.getProperty("user.dir");
        }else{
            way = state.getAbstractPath();
        }
        File dir = new File(way);
        String childs[] = dir.list();
        for(String child: childs){
                System.out.println(child);
            }
        Message m = new LsReply(childs);
        m.send(oos);
    }
     
}
