/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

/**
 *
 * @author aqdaycuklu
 */
public class MenuBar extends javax.swing.JMenuBar {

    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuTree;
    private javax.swing.JMenu jMenuInfo;

    public MenuBar(aqbitig.gizehter.controller.bridge.InterfaceMenuBar interfaceMenuBar) {

        this.add(menuFile(interfaceMenuBar));
        this.add(menuTree(interfaceMenuBar));
        this.add(menuInfo(interfaceMenuBar));

    }

    private javax.swing.JMenu menuFile(aqbitig.gizehter.controller.bridge.InterfaceMenuBar interfaceMenuBar) {
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

        javax.swing.JMenuItem jMenuItemSave = new javax.swing.JMenuItem("Save");
        jMenuItemSave.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.fileSave();
        });
        this.jMenuFile.add(jMenuItemSave);

        javax.swing.JMenuItem jMenuItemClose = new javax.swing.JMenuItem("Close");
        jMenuItemClose.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.fileClose();
        });
        this.jMenuFile.add(jMenuItemClose);

        javax.swing.JPopupMenu.Separator jSeparatorFile = new javax.swing.JPopupMenu.Separator();
        this.jMenuFile.add(jSeparatorFile);

        javax.swing.JMenuItem jMenuItemExit = new javax.swing.JMenuItem("Exit");
        jMenuItemExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.fileExit();
        });
        this.jMenuFile.add(jMenuItemExit);

        return this.jMenuFile;
    }

    private javax.swing.JMenu menuTree(aqbitig.gizehter.controller.bridge.InterfaceMenuBar interfaceMenuBar) {
        this.jMenuTree = new javax.swing.JMenu("Tree");

        javax.swing.JMenuItem jMenuItemAddBranch = new javax.swing.JMenuItem("Add Branch");
        jMenuItemAddBranch.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.treeAddBranch();
        });
        jMenuTree.add(jMenuItemAddBranch);

        javax.swing.JMenuItem jMenuItemAddLeaf = new javax.swing.JMenuItem("Add Leaf");
        jMenuItemAddLeaf.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.treeAddLeaf();
        });
        jMenuTree.add(jMenuItemAddLeaf);

        javax.swing.JMenuItem jMenuItemRemove = new javax.swing.JMenuItem("Remove");
        jMenuItemRemove.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.treeRemove();
        });
        jMenuTree.add(jMenuItemRemove);

        javax.swing.JMenuItem jMenuItemReload = new javax.swing.JMenuItem("Reload");
        jMenuItemReload.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.treeReload();
        });
        jMenuTree.add(jMenuItemReload);

        javax.swing.JPopupMenu.Separator jSeparatorTree = new javax.swing.JPopupMenu.Separator();
        jMenuTree.add(jSeparatorTree);

        javax.swing.JMenuItem jMenuItemClear = new javax.swing.JMenuItem("Clear");
        jMenuItemClear.addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceMenuBar.treeClear();
        });
        jMenuTree.add(jMenuItemClear);

        return this.jMenuTree;
    }

    private javax.swing.JMenu menuInfo(aqbitig.gizehter.controller.bridge.InterfaceMenuBar interfaceMenuBar) {
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

    public javax.swing.JMenu getMenuTree() {
        return this.jMenuTree;
    }

    public javax.swing.JMenu getMenuInfo() {
        return this.jMenuInfo;
    }

    public void menu(boolean activate) {
        getMenuTree().setEnabled(activate);
        getMenuFile().getItem(2).setEnabled(activate);
        getMenuFile().getItem(3).setEnabled(activate);
    }
}
