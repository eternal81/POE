/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import POE.Parser;
import POE.poeThread;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;
import temp.TestWorker;

/**
 *
 * @author john
 */
public class GUIFrame extends javax.swing.JFrame {

    private TableWorker tw;

    private String[][] testdata() {
        String[][] data = new String[MyProps.getDefault().getThreadURLs().size()][4];
        int index = 0;
        for (Map.Entry<String, String> entry : MyProps.getDefault().getThreadURLs().entrySet()) {
            data[index] = new String[]{entry.getKey(), entry.getValue(), "Waiting", "0"};
            index++;
        }
        return data;
    }

    /**
     * Creates new form GUIFrame
     */
    public GUIFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jStatusTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jStatusTable.setModel(new javax.swing.table.DefaultTableModel(
            this.testdata(),
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jStatusTable);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 375, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 25, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .add(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 275, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private class TableWorker extends SwingWorker<Boolean, TableModel> {

        @Override
        protected Boolean doInBackground() throws Exception {
            TableModel tm = jStatusTable.getModel();
            int numThreads = 0;
            while (!isCancelled()) {
                int index = 0;
                for (Map.Entry<String, String> entry : MyProps.getDefault().getThreadURLs().entrySet()) {
                    tm.setValueAt("Working", index, 2);
                    publish(tm);
                    String url = entry.getValue();
                    List<poeThread> threads = Parser.getThreads(url);
                    for (int i = 2; i < 15; i++) {
                        numThreads += threads.size();
                        tm.setValueAt(numThreads, index, 3);
                        publish(tm);
                        try {
                            //pause 5 seconds between each thread listing                            
                            Thread.sleep(1000 * 5);
                        } catch (InterruptedException ex) {
                            //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String domURL = url + "/page/" + i;
                        threads = Parser.getThreads(domURL);

                    }
                    index++;
                }
            }
            return true;
        }

        @Override
        protected void process(List<TableModel> chunks) {
            TableModel chunk = chunks.get(chunks.size() - 1);

            jStatusTable.setModel(chunk);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TestWorker t = new TestWorker( jStatusTable);
        t.execute();
        //handle all of the updates ext outside of the gui!!!
//        tw = new TableWorker();
//        tw.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        tw.cancel(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;


                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jStatusTable;
    // End of variables declaration//GEN-END:variables
}
