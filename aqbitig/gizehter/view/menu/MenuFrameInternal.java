package aqbitig.gizehter.view.menu;

import aqbitig.lib.basic.T;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aqdaycuklu
 */
public class MenuFrameInternal extends javax.swing.JMenuBar {

    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuTree;
    private aqbitig.gizehter.view.MyTree myTree;
    private aqbitig.gizehter.controller.MyTreeModel myTreeModel;

    public MenuFrameInternal(aqbitig.gizehter.view.MyTree myTree) {
        this.myTree = myTree;
        this.myTreeModel = (aqbitig.gizehter.controller.MyTreeModel) myTree.getModel();
        this.add(menuFile());
        this.add(menuTree());
    }

    private javax.swing.JMenu menuFile() {
        this.jMenuFile = new javax.swing.JMenu("File");

        javax.swing.JMenuItem jMenuItemSave = new javax.swing.JMenuItem("Save");
        jMenuItemSave.addActionListener((java.awt.event.ActionEvent evt) -> {
            //interfaceMenuBar.fileSave();
            myTreeModel.save();
        });
        this.jMenuFile.add(jMenuItemSave);

        javax.swing.JMenuItem jMenuItemClose = new javax.swing.JMenuItem("Close");
        jMenuItemClose.addActionListener((java.awt.event.ActionEvent evt) -> {

        });
        this.jMenuFile.add(jMenuItemClose);

        return this.jMenuFile;
    }

    private javax.swing.JMenu menuTree() {
        this.jMenuTree = new javax.swing.JMenu("Tree");

        javax.swing.JMenuItem jMenuItemAddBranch = new javax.swing.JMenuItem("Add Branch");
        jMenuItemAddBranch.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.myTreeModel.addBranch(this.myTree.getSelectionPath());
        });
        jMenuTree.add(jMenuItemAddBranch);

        javax.swing.JMenuItem jMenuItemAddLeaf = new javax.swing.JMenuItem("Add Leaf");
        jMenuItemAddLeaf.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.myTreeModel.addLeaf(myTree.getSelectionPath());
        });
        jMenuTree.add(jMenuItemAddLeaf);

        javax.swing.JMenuItem jMenuItemRemove = new javax.swing.JMenuItem("Remove");
        jMenuItemRemove.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.myTreeModel.remove(myTree.getSelectionPath());
        });
        jMenuTree.add(jMenuItemRemove);

        javax.swing.JMenuItem jMenuItemReload = new javax.swing.JMenuItem("Reload");
        jMenuItemReload.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.myTreeModel.reload();
        });
        jMenuTree.add(jMenuItemReload);

        javax.swing.JPopupMenu.Separator jSeparatorTree = new javax.swing.JPopupMenu.Separator();
        jMenuTree.add(jSeparatorTree);

        javax.swing.JMenuItem jMenuItemClear = new javax.swing.JMenuItem("Clear");
        jMenuItemClear.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.myTreeModel.clear();
        });
        jMenuTree.add(jMenuItemClear);

        return this.jMenuTree;
    }

}
