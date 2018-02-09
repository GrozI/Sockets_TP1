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
public class CdRequest extends Message{
    String path;

    public CdRequest(String path) {
        this.path = path;
    }

    @Override
    public void handle(ObjectOutputStream oos, State state) {
        System.out.println(state.getAbstractPath());
        String response = null;
        boolean result = false;
        String way = "";
        if (state.getAbstractPath() == ""){
            way = path;
        }
        else{
            way = state.getAbstractPath()+"\\"+path;
        }
        
        File file = new File(way);
        System.out.println("file: "+file.getPath());
        
        if(file.exists()){
            if (file.isDirectory()){
                state.cd(path);
                result = true;
                response = state.getAbstractPath();
            }
            else{
                response = path+" n'est pas un répertoire";
            }
        }
        else{
            response = path+" n'existe pas";
        }
        Message m = new CdReply(result,response);
        m.send(oos);
        System.out.println(state.getAbstractPath());
    }

    
}
