/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.controller;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.basic.T;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

/**
 *
 * @author aqdaycuklu
 */
public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object node,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, node, selected, expanded, leaf, row, hasFocus);
        T.o();

        // Assuming you have a tree of Strings
        //DefaultMutableTreeNode o = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        DefaultMutableTreeNode o = (DefaultMutableTreeNode) node;

        Object obj = o.getUserObject();
        if (leaf && obj instanceof MyAtomic && ((MyAtomic)obj).getId() == 0) {
            T.o("string object: " + obj);
            setForeground(new Color(128, 0, 0));
        }

        return this;
    }
}
