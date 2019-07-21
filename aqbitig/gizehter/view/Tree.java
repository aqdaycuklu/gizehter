package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.MyTransferHandler;
import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.gizehter.controller.MyTreeModelListener;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class Tree extends javax.swing.JTree {

    /**
     *
     * @param abstractTree
     */
    public Tree(aqbitig.gizehter.controller.AbstractTree abstractTree) {

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    aqbitig.gizehter.view.menu.MenuPopup popupMenu = new aqbitig.gizehter.view.menu.MenuPopup((javax.swing.JTree) e.getComponent(), e.getX(), e.getY());
                } else {
                    abstractTree.leafSelected();
                }
            }
        });

        MyTreeModel myTreeModel = new MyTreeModel(abstractTree);
        myTreeModel.addTreeModelListener(new MyTreeModelListener());
        // if ("open".equalsIgnoreCase(aqbitig.gizehter.view.Main.mode)) {
        //     myTreeModel.populateTree();
        // }

        setDragEnabled(true);
        setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        setEditable(true);
        setModel(myTreeModel);
        getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        setShowsRootHandles(true);
        setTransferHandler(new MyTransferHandler());
        setVisible(true);

    }

}
