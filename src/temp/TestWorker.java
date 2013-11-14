/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;

/**
 *
 * @author john
 */
public class TestWorker extends SwingWorker<Boolean, TableModel> {

    private TableModel tm;
    private JTable jt;

    public TestWorker( JTable _jt) {
        this.tm = _jt.getModel();
        this.jt = _jt;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        int numThreads = 0;
        while (!isCancelled()) {
            for (int index = 0; index < 10; index++) {
                tm.setValueAt(index, 0, 2);
                publish(tm);
            }
        }
        return null;
    }

    @Override
    protected void process(List<TableModel> chunks) {
        TableModel chunk = chunks.get(chunks.size() - 1);

        jt.setModel(chunk);
    }
}
