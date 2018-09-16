package aqbitig.controller;

import aqbitig.lib.T;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyTreeModelListener implements TreeModelListener {

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        T.o();
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        T.o();
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        T.o();
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        T.o();
    }

}
