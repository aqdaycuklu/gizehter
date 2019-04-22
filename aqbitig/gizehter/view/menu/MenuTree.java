/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view.menu;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author aqdaycuklu
 */
public class MenuTree extends javax.swing.JPopupMenu {

    public MenuTree(javax.swing.JTree jTree, int x, int y) {

        aqbitig.gizehter.controller.MyTreeModel treeModel = (aqbitig.gizehter.controller.MyTreeModel) jTree.getModel();

        javax.swing.JMenuItem jMenuItemRename = new javax.swing.JMenuItem("Rename");
        jMenuItemRename.addActionListener((java.awt.event.ActionEvent evt) -> {


            jTree.setFocusable(true);
            jTree.setRequestFocusEnabled(true);
            jTree.setEditable(true);
            jTree.requestFocus();
            jTree.requestFocusInWindow();
            //jTree.setInvokesStopCellEditing(true);

            jTree.getCellEditor().getTreeCellEditorComponent(jTree, (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent(), true, true, true,((DefaultMutableTreeNode) jTree.getLastSelectedPathComponent()).getParent().getIndex((DefaultMutableTreeNode) jTree.getLastSelectedPathComponent())).setFocusable(true);
        });
        this.add(jMenuItemRename);

        javax.swing.JPopupMenu.Separator jSeparator = new javax.swing.JPopupMenu.Separator();
        this.add(jSeparator);

        javax.swing.JMenuItem jMenuItemAddBranch = new javax.swing.JMenuItem("Add Branch");
        jMenuItemAddBranch.addActionListener((java.awt.event.ActionEvent evt) -> {
            treeModel.addBranch(jTree.getSelectionPath());
        });
        this.add(jMenuItemAddBranch);

        javax.swing.JMenuItem jMenuItemAddLeaf = new javax.swing.JMenuItem("Add Leaf");
        jMenuItemAddLeaf.addActionListener((java.awt.event.ActionEvent evt) -> {
            treeModel.addLeaf(jTree.getSelectionPath());
        });
        this.add(jMenuItemAddLeaf);

        javax.swing.JMenuItem jMenuItemRemove = new javax.swing.JMenuItem("Remove");
        jMenuItemRemove.addActionListener((java.awt.event.ActionEvent evt) -> {
            treeModel.remove(jTree.getSelectionPath());
        });
        this.add(jMenuItemRemove);

        javax.swing.JMenuItem jMenuItemReload = new javax.swing.JMenuItem("Reload");
        jMenuItemReload.addActionListener((java.awt.event.ActionEvent evt) -> {
            treeModel.reload();
        });
        this.add(jMenuItemReload);

        this.show(jTree, x, y);
    }

}
