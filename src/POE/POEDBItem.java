/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POE;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author john
 */
public class POEDBItem extends POEItem {

    private String co = "";
    private String bo = "";
    private String gbo = "";
    private int ItemListID;

    public POEDBItem() {
    }

    private int getIntRequirement(String key) {
        try {
            if (super.getRequirements() != null) {
                Requirements[] r = super.getRequirements();
                for (Requirements req : r) {
                    if (req.getName().matches(key)) {
                        List temp = (List) req.getValues().get(0);
                        return Integer.parseInt(temp.get(0).toString().replaceAll(" \\(gem\\)", ""));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getRequiredLevel() {
        return getIntRequirement("Level");
    }

    public int getRequiredDex() {
        return getIntRequirement("Dex");
    }

    public int getRequiredStr() {
        return getIntRequirement("Str");
    }

    public int getRequiredInt() {
        return getIntRequirement("Int");
    }

    private int getIntProperty(String key) {
        try {
        if (super.getProperties() != null) {
            for (Properties p : super.getProperties()) {
                if (p.getName().matches(key)) {
                    //the value is the first value in the inner array
                    List<String> d = (List<String>) p.getValues().get(0);
                    //get rid of the non numeric portion if it exists
                    String ret = d.get(0).replaceAll("\\+", "").replaceAll("\\%", "");
                    //value is stored as string... convert to int
                    return Integer.parseInt(ret);
                }
            }
        }}
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private BigDecimal getDecProperty(String key) {
        try {
        if (super.getProperties() != null) {
            for (Properties p : super.getProperties()) {
                if (p.getName().matches(key)) {
                    //the value is the first value in the inner array
                    List<String> d = (List<String>) p.getValues().get(0);
                    //get rid of the non numeric portion if it exists
                    String ret = d.get(0).replaceAll("\\+", "").replaceAll("\\%", "");
                    //value is stored as string... convert to int
                    return BigDecimal.valueOf(Double.parseDouble(ret));
                }
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return BigDecimal.valueOf(0);
    }

    private String getStringProperty(String key) {
        try {
        if (super.getProperties() != null) {
            for (Properties p : super.getProperties()) {
                if (p.getName().matches(key)) {
                    //the value is the first value in the inner array
                    List<String> d = (List<String>) p.getValues().get(0);
                    //get rid of the non numeric portion if it exists
                    return d.get(0).replaceAll("\\+", "").replaceAll("\\%", "");
                    //value is stored as string... convert to int
                    //return Integer.parseInt(ret);
                }
            }
        }}
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private String getStringProperty(String key, int index) {
        try {
        if (super.getProperties() != null) {
            for (Properties p : super.getProperties()) {
                if (p.getName().matches(key)) {
                    //the value is the first value in the inner array
                    List<List> d = (List<List>) p.getValues();
                    //The type of damage is the second part of the array.. might be more than one
                    for (List temp : d) {
                        //only return the array value that matches the correct damage type
                        if (Integer.parseInt(temp.get(1).toString()) == index) {
                            return temp.get(0).toString();
                        }
                    }
                }
            }
        }}
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public String getPhysicalDamage() {
        return getStringProperty("Physical Damage");
    }

    public String getColdDamage() {
        return getStringProperty("Elemental Damage", 5);
    }

    public String getFireDamage() {
        return getStringProperty("Elemental Damage", 4);
    }

    public String getLightningDamage() {
        return getStringProperty("Elemental Damage", 6);
    }

    public BigDecimal getCritChance() {
        return getDecProperty("Critical Strike Chance");
    }

    public BigDecimal getAPS() {
        return getDecProperty("Attacks per Second");
    }

//    public String getName() {
//        return "";//getStringProperty("name");
//    }

    public int getEnergyShield() {
        return getIntProperty("Energy Shield");
    }

    public int getArmour() {
        return getIntProperty("Armour");
    }

    public int getEvasionRating() {
        return getIntProperty("Evasion Rating");
    }

    public int getBlock() {
        return getIntProperty("Chance to Block");
    }

    public int getQuality() {
        return getIntProperty("Quality");
    }

    public String getSocketString() {
        try {
            //shrink down socket colors and combos into a single value for the database
            String[] ret = {"", "", "", "", "", ""};
            if (this.getSockets() != null) {
                for (Sockets temp : super.getSockets()) {
                    ret[temp.getGroup().intValue()] += temp.getAttr();
                }
            }
            return ret[0] + " - " + ret[1] + " - " + ret[2] + " - " 
                    + ret[3] + " - " + ret[4] + " - " + ret[5];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @return the ThreadID
     */
    public int getItemListID() {
        return ItemListID;
    }

    /**
     * @param ThreadID the ThreadID to set
     */
    public void setItemListID(int ItemListID) {
        this.ItemListID = ItemListID;
    }

    /**
     * @return the co
     */
    public String getCo() {
        return co;
    }

    /**
     * @param co the co to set
     */
    public void setCo(String co) {
        this.co = co;
    }

    /**
     * @return the bo
     */
    public String getBo() {
        return bo;
    }

    /**
     * @param bo the bo to set
     */
    public void setBo(String bo) {
        this.bo = bo;
    }

    /**
     * @return the gbo
     */
    public String getGbo() {
        return gbo;
    }

    /**
     * @param gbo the gbo to set
     */
    public void setGbo(String gbo) {
        this.gbo = gbo;
    }
}
