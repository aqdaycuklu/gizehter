/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.controller;

import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.gizehter.view.Info;
import aqbitig.lib.basic.T;
import java.io.File;
import javax.swing.JOptionPane;
import aqbitig.gizehter.controller.bridge.InterfaceFileChooser;

/**
 *
 * @author aqdaycuklu
 */
public class MyMenuBar implements aqbitig.gizehter.controller.bridge.InterfaceMenuBar {

    public aqbitig.gizehter.controller.MyTreeModel myTreeModel;
    public aqbitig.gizehter.view.Main main;
    public aqbitig.gizehter.view.Info info;
    public aqbitig.gizehter.view.Tree tree;

    public MyMenuBar(aqbitig.gizehter.view.Main main) {
        this.main = main;
    }

    @Override
    public void fileNew() {
        T.o();
        main.desktopPane.add(new aqbitig.gizehter.view.InternalFrame(main, "new"));
    }

    @Override
    public void fileOpen() {
        T.o();
        main.desktopPane.add(new aqbitig.gizehter.view.InternalFrame(main, "open"));
    }

    @Override
    public void fileSave() {
        this.myTreeModel.save();
        JOptionPane.showMessageDialog(null, "saved");
    }

    @Override
    public void fileClose() {
        this.myTreeModel.clear();
        ((Info) this.main.splitPane.getInfo()).clear();
        this.main.getContentPane().removeAll();
        this.main.menuBar.menu(false);
        this.main.pack();
    }

    @Override
    public void fileExit() {
        System.exit(0);
    }

    @Override
    public void treeAddBranch() {
        this.myTreeModel.addBranch(this.tree.getSelectionPath());
    }

    @Override
    public void treeAddLeaf() {
        this.myTreeModel.addLeaf(this.tree.getSelectionPath());
    }

    @Override
    public void treeRemove() {
        this.myTreeModel.remove(this.tree.getSelectionPath());
    }

    @Override
    public void treeReload() {
        this.myTreeModel.reload();
    }

    @Override
    public void treeClear() {
        this.myTreeModel.clear();
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

    public void setInternalFrame(aqbitig.gizehter.view.SplitPane splitPane) {
        this.main.menuBar.menu(true);
        this.main.myMenuBar.info = splitPane.getInfo();
        this.main.myMenuBar.tree = splitPane.getTree();
        this.main.myMenuBar.myTreeModel = ((MyTreeModel) splitPane.getTree().getModel());
    }
}
