/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author john
 */
public class MainGUI extends JFrame {

    public final int windowW = 640;
    public final int windowH = 480;
    public final int defaultPadding = 5;

    public MainGUI() {
        initUI();
    }

    private void initUI() {
        // <editor-fold defaultstate="collapsed" desc="panel">      
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);
        // </editor-fold>        
        // <editor-fold defaultstate="collapsed" desc="quitButton"> 
        JButton quitButton = new JButton("Quit");
        //xywh - 640-80x480-30
        quitButton.setBounds(
                570,
                420,
                80, 30);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        panel.add(quitButton);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="jStatusTable"> 
        String[][] rowdata = new String[][]{
            {"","","",""},
            {"","","",""}
            };
        String[] columns = new String[]{"Thread", "Url", "Status", "Count"};
        JTable jStatusTable = new JTable(rowdata, columns);
        jStatusTable.setBounds(50, 50, 300, 300);
        panel.add(jStatusTable);
        // </editor-fold>
        setTitle("Test GUI");
        setResizable(false);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI ex = new MainGUI();
                ex.setVisible(true);
            }
        });
    }
}
