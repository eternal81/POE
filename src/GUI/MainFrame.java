/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import POE.Parser;
import POE.poeThread;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
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

        jbutStart = new javax.swing.JButton();
        jbutStop = new javax.swing.JButton();
        jtxtURL = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbutStart.setText("Start");
        jbutStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutStartActionPerformed(evt);
            }
        });

        jbutStop.setText("Stop");

        jtxtURL.setText("http://www.pathofexile.com/forum/view-forum/427");
        jtxtURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtURLActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jmenuExit.setText("Exit");
        jmenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuExitActionPerformed(evt);
            }
        });
        jMenu1.add(jmenuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .add(jbutStart)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jbutStop))
            .add(layout.createSequentialGroup()
                .add(jtxtURL)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jtxtURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 209, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbutStart)
                    .add(jbutStop))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmenuExitActionPerformed

    private void jtxtURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtURLActionPerformed

    private void jbutStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutStartActionPerformed
        String url = this.jtxtURL.getText();
        List<poeThread> threads = Parser.getThreads(url);
        
        for (int i = 2; i < 15; i++) {
                    try {
                        //pause 5 seconds between each thread listing
                        //Logger.getLogger("MyInfo").info("Iterating through pages -> " + i);
                        Thread.sleep(1000 * 5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            String domURL = url + "/page/" + i;
            threads = Parser.getThreads(domURL);
        }
    }//GEN-LAST:event_jbutStartActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jbutStart;
    private javax.swing.JButton jbutStop;
    private javax.swing.JMenuItem jmenuExit;
    private javax.swing.JTextField jtxtURL;
    // End of variables declaration//GEN-END:variables
}
