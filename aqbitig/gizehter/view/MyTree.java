package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.MyTransferHandler;
import aqbitig.lib.basic.T;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

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
    public MyTree() {

        // if ("open".equalsIgnoreCase(aqbitig.gizehter.view.Main.mode)) {
        //     myTreeModel.populateTree();
        // }

        // TREE
        this.setDragEnabled(true);
        this.setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        this.setEditable(true);
        this.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.setShowsRootHandles(true);
        this.setTransferHandler(new MyTransferHandler());

    }

}
