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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
        temp3();
    }

    /**
     * Used to move explicit mods to its own table (very long process for 300k+ records 
     * expanding to about a million entries
     */
    public static void temp3() {
        Connection conn = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(MyProps.getDefault().getConnectionString());
            s = conn.createStatement();
            //s = conn.prepareStatement();
            HashMap<String, Integer> mods = new HashMap<String, Integer>();
            ResultSet rs = s.executeQuery("SELECT ModText, ModID FROM Mods_List ORDER BY ModID");
            while (rs.next()) {
                mods.put(rs.getString("ModText").toLowerCase(), rs.getInt("ModID"));
            }
            for (int selset = 0; selset < Integer.MAX_VALUE; selset += 100) {
                System.out.println(selset);
                //This has to be done in batches... limited heap space
                rs = s.executeQuery("SELECT * FROM Items LIMIT " + selset + "," + 100);
                String sql = "INSERT INTO ItemMods (ItemID, Implicit, Mods_ListID, minV, maxV) VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                while (rs.next()) {

                    String[] temp = rs.getString("_explicitMods").split(",");
                    int ItemID = rs.getInt("ItemID");
                    for (String s1 : temp) {
                        explicitMod m = new explicitMod(s1);
                        if (!mods.containsKey(m.getText().toLowerCase())) {
                            System.out.println("No matching explicit mod found for: " + m.getText());
                        }
                        int ModIndex = mods.get(m.getText().toLowerCase());
                        
                        
                        ps.setInt(1, rs.getInt("ItemID"));
                        ps.setBoolean(2, false);
                        ps.setInt(3, ModIndex);
                        ps.setInt(4, m.getMinValue());
                        ps.setInt(5, m.getMaxValue());
                        ps.addBatch();
                        //System.out.println(rs.getRow());
                    }
                }
                ps.executeBatch();
                ps.close();
                ps = null;
                rs.close();
                rs = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    public static void temp2() {
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
}
