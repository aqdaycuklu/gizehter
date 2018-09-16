package aqbitig.controller;

import aqbitig.gizehter.view.Main;
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

    public MyTree(InterfaceTree iface) {

        MyTreeModel myTreeModel = new MyTreeModel();
        myTreeModel.addTreeModelListener(new MyTreeModelListener());
        if ("open".equalsIgnoreCase(Main.mode)) {
            myTreeModel.populateTree();
        }
        this.setModel(myTreeModel);
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
