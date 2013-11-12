/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POE;

import java.util.List;

/**
 *
 * @author john
 */
public class AdditionalProperties {
    private Number displayMode;
    private String name;
    private List values;
    private Number progress;
    
    public AdditionalProperties(){
        
    }

    /**
     * @return the displayMode
     */
    public Number getDisplayMode() {
        return displayMode;
    }

    /**
     * @param displayMode the displayMode to set
     */
    public void setDisplayMode(Number displayMode) {
        this.displayMode = displayMode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the values
     */
    public List getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List values) {
        this.values = values;
    }

    /**
     * @return the progress
     */
    public Number getProgress() {
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(Number progress) {
        this.progress = progress;
    }
}
