/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import GUI.MyProps;
import POE.Parser;
import POE.poeThread;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class Test {

    public static void main(String[] args) throws IOException {
        //String[] urls = (String[]) (MyProps.getDefault().getThreadURLs().values().toArray());
        List<Object[]> threads = new ArrayList<Object[]>();
        //just do domination
        String url = "http://www.pathofexile.com/forum/view-forum/427/page/";
        boolean cont = true;
        int page = 118;
        while (cont) {
            List<Object[]> thread = Parser.getThreads2(url + page);
            if (thread.size() == 0) {
                cont = false;
            } else {
                threads.addAll(thread);
                page++;
                System.out.println(threads.size());
            }
        }
        System.out.println("done");
    }

    public static void temp() {
        Connection conn = null;
        Statement s = null;
        List<String> mods;
        mods = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(MyProps.getDefault().getConnectionString());
            s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT _explicitMods FROM Items");
            String[] temp;
            while (rs.next()) {
                temp = rs.getString("_explicitMods").split(",");
                for (String split : temp) {
                    explicitMod m = new explicitMod(split);
                    String replace = "REPLACE INTO Mods_List (ModText) VALUES ('" + m.getText() + "')";
                    s = conn.createStatement();
                    s.execute(replace);
                    s.close();
                    System.out.println(rs.getRow());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
    }

    private String[] splitString(String str) {
        String[] temp = {"", ""};

        return temp;
    }
}
