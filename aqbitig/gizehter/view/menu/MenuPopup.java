/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view.menu;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aqdaycuklu
 */
public class MenuPopup extends javax.swing.JPopupMenu {

    public MenuPopup(javax.swing.JTree jTree, int x, int y) {

        aqbitig.gizehter.controller.MyTreeModel treeModel = (aqbitig.gizehter.controller.MyTreeModel) jTree.getModel();

        javax.swing.JMenuItem jMenuItemRename = new javax.swing.JMenuItem("Rename");
        jMenuItemRename.addActionListener((java.awt.event.ActionEvent evt) -> {

            jTree.requestFocusInWindow();
            jTree.setInvokesStopCellEditing(true);
            try {
                Robot r = new Robot();
                r.keyPress(KeyEvent.VK_F2);  // VK_WINDOWS key still pressed
                r.keyRelease(KeyEvent.VK_F2);
            } catch (AWTException ex) {
                Logger.getLogger(MenuPopup.class.getName()).log(Level.SEVERE, null, ex);
            }

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
