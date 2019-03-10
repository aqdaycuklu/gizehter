/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.InterfaceFileChooser;
import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.lib.basic.T;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import aqbitig.gizehter.view.menu.MenuFrameInterface;

/**
 *
 * @author aqdaycuklu
 */
public class Main extends javax.swing.JFrame {

    private javax.swing.JDesktopPane desktopPane;

    private aqbitig.gizehter.view.menu.MenuFrame menuBar;

    public Main() {

        /* LOOK AND FEEL */
        lookAndFeel();

        /* MENUBAR */
        menuBar();

        /* WINDOW POSITION */
        windowPosition();

        /* CONTENT PANE */
        contentPane();
        pack();
        
        /* DEV */
        internalFrame("open");
    }

    private void lookAndFeel() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void menuBar() {
        this.menuBar = new aqbitig.gizehter.view.menu.MenuFrame(new MenuFrameInterface() {
            @Override
            public void fileNew() {
                internalFrame("new");
            }

            @Override
            public void fileOpen() {
                internalFrame("open");
            }

            @Override
            public void fileExit() {
                System.exit(0);
            }

            @Override
            public void infoAbout() {
                JOptionPane.showMessageDialog(null, "AqBitig 2018\nhttps://www.aqbitig.de\naqdaycuklu@aqbitig.de");
            }

            @Override
            public void infoLicence() {
                String licenses = "Used third party libs/software:\n\nSQLite JDBC\nhttps://bitbucket.org/xerial/sqlite-jdbc/";
                JOptionPane.showMessageDialog(null, licenses);
            }
        });

        setJMenuBar(menuBar);

    }

    private void windowPosition() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //setMinimumSize(new java.awt.Dimension((int) (dim.width * 0.875), (int) (dim.height * 0.875)));
        //setSize(new java.awt.Dimension((int) (dim.width * 0.875), (int) (dim.height * 0.875)));
        setPreferredSize(new java.awt.Dimension((int) (dim.width * 0.875), (int) (dim.height * 0.5)));
        setLocation((int) (dim.width * 0.125) / 2, (int) (dim.height * 0.125) / 2);

        //setBounds((int)(dim.width * 0.125)/2,  (int)(dim.height * 0.125)/2, (int) (dim.width * 0.875), (int) (dim.height * 0.875));
    }

    private void contentPane() {
        desktopPane = new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setContentPane(desktopPane);
    }

    private void internalFrame(String mode) {
        T.o("mode:" + mode);
        aqbitig.gizehter.view.InternalFrame internalFrame = new aqbitig.gizehter.view.InternalFrame(mode);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
    }

    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
