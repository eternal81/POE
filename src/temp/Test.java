/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import GUI.MyProps;
import POE.Parser;
import POE.poeThread;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class Test {
    public static void main(String[] args) throws IOException{
        System.out.println(MyProps.getDefault().getConnectionString());
                int index = 0;
        for (Map.Entry<String, String> entry : MyProps.getDefault().getThreadURLs().entrySet()) {
//            this.jTable1.getModel().setValueAt("WORKING...", 0 , 0);
//            this.jTable1.getModel().setValueAt("Page 1", 0, 1);
////            jtblStatusBoard.setModel(dtm);
//            jtblStatusBoard.validate();
            String url = entry.getValue();
            List<poeThread> threads = Parser.getThreads(url);

            for (int i = 2; i < 15; i++) {
                //jTable1.getModel().setValueAt("Page " + i, index, 3);
                try {
                    //pause 5 seconds between each thread listing
                    //Logger.getLogger("MyInfo").info("Iterating through pages -> " + i);
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                String domURL = url + "/page/" + i;
                threads = Parser.getThreads(domURL);
            }
            index++;
        }
    }
}
