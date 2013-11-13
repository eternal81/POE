/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.table.TableModel;

/**
 *
 * @author john
 */
public class StatusUpdates {
    TableModel tm;
    public StatusUpdates(TableModel tm){
        this.tm = tm;
        
    }
    
    public void doAction(){
        tm.setValueAt("Test", 0, 0);
    }
    
}
