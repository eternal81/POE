/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POE;

import GUI.MyProps;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class DBHandler {

    private Connection conn = null;
    private Statement s = null;
    
    public DBHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(MyProps.getDefault().getConnectionString());
            s = conn.createStatement();

        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public void addThread(poeThread thread) {
        try {
            CallableStatement cs = conn.prepareCall("{call addThread(?,?,?,?,?)}");
            cs.setString(1, thread.getThreadID());
            cs.setString(2, thread.getUser());
            cs.setString(3, convPoeDate(thread.getDatePosted()));
            cs.setString(4, convPoeDate(thread.getDateLast()));
            cs.setString(5, "");

            cs.executeUpdate();

            addItems(thread);

        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addItems(poeThread thread) {
        ArrayList<POEDBItem> items = thread.getItems();
        PreparedStatement ps = null;
        try {
            //remove all the old entries
            PreparedStatement clear = conn.prepareStatement("DELETE FROM Items WHERE _threadID = \"" + thread.getThreadID() + "\"");
            clear.execute();
            
            for (POEDBItem item : items) {               
                ps = conn.prepareStatement(MyProps.getDefault().getInsertString());
                ps.setString(1, thread.getThreadID());
                ps.setBoolean(2, item.getVerified());
                ps.setInt(3, item.getW().intValue());
                ps.setInt(4, item.getH().intValue());
                ps.setBoolean(5, item.getSupport());
                ps.setString(6, item.getLeague());
                ps.setString(7, item.getSocketString());
                ps.setString(8, item.getName());
                ps.setString(9, item.getTypeLine());
                ps.setBoolean(10, item.getIdentified());
                ps.setInt(11, item.getEnergyShield());
                ps.setInt(12, item.getEvasionRating());
                ps.setInt(13, item.getArmour());
                ps.setInt(14, item.getBlock());
                ps.setInt(15, item.getQuality());
                ps.setString(16, item.getPhysicalDamage());
                ps.setString(17, item.getColdDamage());
                ps.setString(18, item.getFireDamage());
                ps.setString(19, item.getLightningDamage());
                ps.setBigDecimal(20, item.getCritChance());
                ps.setBigDecimal(21, item.getAPS());
                ps.setInt(22, item.getRequiredLevel());
                ps.setInt(23, item.getRequiredInt());
                ps.setInt(24, item.getRequiredDex());
                ps.setInt(25, item.getRequiredStr());
                ps.setString(26, item.getExplicitModsAsString());
                ps.setString(27, item.getImplicitModsAsString());
                ps.setString(28, item.getFlavorTextAsString());
                ps.setInt(29, item.getFrameType().intValue());
                //ps.setString(30, item.getSocketItemsAsString());
                ps.setString(30, item.getIcon());
                ps.setString(31, item.getCo());
                ps.setString(32, item.getBo());
                ps.setInt(33, item.getItemListID());
                ps.execute();
                //ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Convert date to MySQL parser format
     *
     * @param in
     * @return
     */
    public static String convPoeDate(java.util.Date in) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(in);
    }
}
