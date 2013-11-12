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
public class Requirements {
    private Number displayMode;
   	private String name;
   	private List values;

        public Requirements(){
            
        }
        
 	public Number getDisplayMode(){
		return this.displayMode;
	}
	public void setDisplayMode(Number displayMode){
		this.displayMode = displayMode;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public List getValues(){
		return this.values;
	}
	public void setValues(List values){
		this.values = values;
	}
}
