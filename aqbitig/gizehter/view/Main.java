/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.ImplementsMenuBar;
import aqbitig.gizehter.controller.InterfaceFileChooser;
import aqbitig.gizehter.controller.InterfaceLogin;
import aqbitig.gizehter.controller.InterfaceMenuBar;
import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.lib.basic.T;
import components.MyInternalFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author aqdaycuklu
 */
public class Main extends javax.swing.JFrame {

    public javax.swing.JDesktopPane desktopPane;

    public aqbitig.gizehter.view.MenuBar menuBar;

    public aqbitig.gizehter.view.SplitPane splitPane;

    private static int sizeX, sizeY, locationX, locationY = 0;

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
        this.menuBar = new aqbitig.gizehter.view.MenuBar(new ImplementsMenuBar(this));
        setJMenuBar(menuBar);
        menuBar.menu(false);

    }

    private void windowPosition() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        /* SET SIZE */
        sizeX = (int) (dim.width * 0.5);
        sizeY = (int) (dim.height * 0.5);
        java.awt.Dimension size = new java.awt.Dimension(sizeX, sizeY);
        setMinimumSize(size);
        setSize(size);

        /* SET LOCATION */
        locationX = (dim.width / 2) - (getSize().width / 2);
        locationY = (dim.height / 2) - (getSize().height / 2);
        setLocation(locationX, locationY);
        setBounds(locationX, locationY, sizeX, sizeY);

        /* CLOSE X */
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    private void contentPane() {
        desktopPane = new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setContentPane(desktopPane);
    }

    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
