package aqbitig.gizehter.controller;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyTree extends javax.swing.JTree {

    /**
     *
     * @param abstractTree
     */
    public MyTree(aqbitig.gizehter.controller.AbstractTree abstractTree) {

        MyTreeModel myTreeModel = new MyTreeModel(abstractTree);
        myTreeModel.addTreeModelListener(new MyTreeModelListener());
       // if ("open".equalsIgnoreCase(aqbitig.gizehter.view.Main.mode)) {
       //     myTreeModel.populateTree();
       // }
        this.setModel(myTreeModel);
        this.setDragEnabled(true);
        this.setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        this.setTransferHandler(new MyTransferHandler());
        this.setEditable(true);
        this.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.setShowsRootHandles(true);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    aqbitig.gizehter.view.menu.MenuPopup popupMenu = new aqbitig.gizehter.view.menu.MenuPopup((javax.swing.JTree) e.getComponent(), e.getX(), e.getY());
                } else {
                    abstractTree.leafSelected();
                }
            }
        });
    }

}
