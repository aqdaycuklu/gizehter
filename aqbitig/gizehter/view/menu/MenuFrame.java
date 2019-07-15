/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view.menu;

/**
 *
 * @author aqdaycuklu
 */
public class MenuFrame extends javax.swing.JMenuBar {

    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuInfo;

    public MenuFrame(aqbitig.gizehter.view.menu.MenuFrameInterface interfaceMenuBar) {

        this.add(menuFile(interfaceMenuBar));
        this.add(menuInfo(interfaceMenuBar));

    }

    private javax.swing.JMenu menuFile(aqbitig.gizehter.view.menu.MenuFrameInterface interfaceMenuBar) {
        this.jMenuFile = new javax.swing.JMenu("File");

        javax.swing.JMenuItem jMenuItemNew = new javax.swing.JMenuItem("New");
        jMenuItemNew.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.fileNew();
        });
        this.jMenuFile.add(jMenuItemNew);

        javax.swing.JMenuItem jMenuItemOpen = new javax.swing.JMenuItem("Open");
        jMenuItemOpen.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.fileOpen();
        });
        this.jMenuFile.add(jMenuItemOpen);

        javax.swing.JPopupMenu.Separator jSeparatorFile = new javax.swing.JPopupMenu.Separator();
        this.jMenuFile.add(jSeparatorFile);

        javax.swing.JMenuItem jMenuItemExit = new javax.swing.JMenuItem("Exit");
        jMenuItemExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.fileExit();
        });
        this.jMenuFile.add(jMenuItemExit);

        return this.jMenuFile;
    }


    private javax.swing.JMenu menuInfo(aqbitig.gizehter.view.menu.MenuFrameInterface interfaceMenuBar) {
        this.jMenuInfo = new javax.swing.JMenu("Info");

        javax.swing.JMenuItem jMenuItemAbout = new javax.swing.JMenuItem("About");
        jMenuItemAbout.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.infoAbout();
        });
        jMenuInfo.add(jMenuItemAbout);

        javax.swing.JMenuItem jMenuItemLicences = new javax.swing.JMenuItem("Licence");
        jMenuItemLicences.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.infoLicence();
        });
        jMenuInfo.add(jMenuItemLicences);

        return this.jMenuInfo;
    }

    public javax.swing.JMenu getMenuFile() {
        return this.jMenuFile;
    }

    public javax.swing.JMenu getMenuInfo() {
        return this.jMenuInfo;
    }

}
