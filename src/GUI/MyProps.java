/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class MyProps {

    private Map<String, String> ThreadURLs;
    private String connectionString;
    private String insertString;
    private String Currencies[];

    public MyProps() {
    }

    public static void setDefault(MyProps props, String filename) {
        try {
            ObjectMapper m = new ObjectMapper();
            m.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            m.writeValue(new File("POEVars.ser"), props);
        } catch (Exception e) {
        }
    }

    public static MyProps getDefault() {
        MyProps props = null;
        try {

            ObjectMapper m = new ObjectMapper();
            props = m.readValue(new File("POEVars.ser"), MyProps.class);
        } catch (IOException ex) {
            Logger.getLogger(MyProps.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    /**
     * @return the ThreadURLs
     */
    public Map<String, String> getThreadURLs() {
        return ThreadURLs;
    }

    /**
     * @param ThreadURLs the ThreadURLs to set
     */
    public void setThreadURLs(Map<String, String> ThreadURLs) {
        this.ThreadURLs = ThreadURLs;
    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * @return the insertString
     */
    public String getInsertString() {
        return insertString;
    }

    /**
     * @param insertString the insertString to set
     */
    public void setInsertString(String insertString) {
        this.insertString = insertString;
    }

    /**
     * @return the Currencies
     */
    public String[] getCurrencies() {
        return Currencies;
    }

    /**
     * @param Currencies the Currencies to set
     */
    public void setCurrencies(String[] Currencies) {
        this.Currencies = Currencies;
    }
}
