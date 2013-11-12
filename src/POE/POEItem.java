/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POE;

import java.util.List;

/**
 * This class is used to sync the bean to the JSON format used by path of exile
 * it is overridden by POEDBItem so that it is compatible with MYSQL
 * @author john
 */
public class POEItem {
    private AdditionalProperties[] additionalProperties;
    private List cosmeticMods;
    private List nextLevelRequirements;
    private List implicitMods;
    private List explicitMods;
    private List flavourText;
    private Number frameType;
    private Number h;
    private String icon;
    private boolean identified;
    private String league;
    private String name;
    private Properties[] properties;
    private Requirements[] requirements;
    private List socketedItems;
    private Sockets[] sockets;
    private boolean support;
    private String typeLine;
    private boolean verified;
    private Number w;
    private String secDescrText;
    private String descrText;

    public POEItem() {
    }

    public List getExplicitMods() {
        return this.explicitMods;
    }

    public String getExplicitModsAsString(){
        if (this.explicitMods != null)
            return this.explicitMods.toString();
        return "";
    }
    public void setExplicitMods(List explicitMods) {
        this.explicitMods = explicitMods;
    }

    public List getFlavourText() {
        return this.flavourText;
    }
    
    public String getFlavorTextAsString(){
        if (flavourText != null)
            return this.flavourText.toString();
        return "";
    }

    public void setFlavourText(List flavourText) {
        this.flavourText = flavourText;
    }

    public Number getFrameType() {
        return this.frameType;
    }

    public void setFrameType(Number frameType) {
        this.frameType = frameType;
    }

    public Number getH() {
        return this.h;
    }

    public void setH(Number h) {
        this.h = h;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getIdentified() {
        return this.identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

    public String getLeague() {
        return this.league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Properties[] getProperties() {
        return this.properties;
    }

    public void setProperties(Properties[] properties) {
        this.properties = properties;
    }

    public Requirements[] getRequirements() {
        return this.requirements;
    }

    public void setRequirements(Requirements[] requirements) {
        this.requirements = requirements;
    }

    public List getSocketedItems() {
        return this.socketedItems;
    }
    
    public String getSocketItemsAsString(){
        if (socketedItems != null)
            return socketedItems.toString();
        return "";
    }

    public void setSocketedItems(List socketedItems) {
        this.socketedItems = socketedItems;
    }

    @Deprecated
    public Sockets[] getSockets() {
        return this.sockets;
    }

    public void setSockets(Sockets[] sockets) {
        this.sockets = sockets;
    }

    public boolean getSupport() {
        return this.support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }

    public String getTypeLine() {
        return this.typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Number getW() {
        return this.w;
    }

    public void setW(Number w) {
        this.w = w;
    }

    /**
     * @return the implicitMods
     */
    public List getImplicitMods() {
        return implicitMods;
    }
    
    public String getImplicitModsAsString(){
        if (implicitMods != null)
            return implicitMods.toString();
        return "";
    }

    /**
     * @param implicitMods the implicitMods to set
     */
    public void setImplicitMods(List implicitMods) {
        this.implicitMods = implicitMods;
    }

    /**
     * @return the additionalProperties
     */
    public AdditionalProperties[] getAdditionalProperties() {
        return additionalProperties;
    }

    /**
     * @param additionalProperties the additionalProperties to set
     */
    public void setAdditionalProperties(AdditionalProperties[] additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return the secDescrText
     */
    public String getSecDescrText() {
        return secDescrText;
    }

    /**
     * @param secDescrText the secDescrText to set
     */
    public void setSecDescrText(String secDescrText) {
        this.secDescrText = secDescrText;
    }

    /**
     * @return the descrText
     */
    public String getDescrText() {
        return descrText;
    }

    /**
     * @param descrText the descrText to set
     */
    public void setDescrText(String descrText) {
        this.descrText = descrText;
    }

    /**
     * @return the nextLevelRequirements
     */
    public List getNextLevelRequirements() {
        return nextLevelRequirements;
    }

    /**
     * @param nextLevelRequirements the nextLevelRequirements to set
     */
    public void setNextLevelRequirements(List nextLevelRequirements) {
        this.nextLevelRequirements = nextLevelRequirements;
    }

    /**
     * @return the cosmeticMods
     */
    public List getCosmeticMods() {
        return cosmeticMods;
    }

    /**
     * @param cosmeticMods the cosmeticMods to set
     */
    public void setCosmeticMods(List cosmeticMods) {
        this.cosmeticMods = cosmeticMods;
    }
}
