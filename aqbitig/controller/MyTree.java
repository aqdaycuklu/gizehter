package aqbitig.controller;

import aqbitig.lib.T;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DropMode;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyTree extends JTree {

    public MyTree(Interface iface) {
        super(new MyTreeModel());

        MyTreeModel myTreeModel = (MyTreeModel) this.getModel();
        myTreeModel.addTreeModelListener(new MyTreeModelListener());
        myTreeModel.populateTree();

        this.setDragEnabled(true);
        this.setDropMode(DropMode.ON_OR_INSERT);
        this.setTransferHandler(new MyTransferHandler());
        this.setEditable(true);
        this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.setShowsRootHandles(true);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                T.o("tool");
                iface.leafSelected();
            }
        });
    }

}
