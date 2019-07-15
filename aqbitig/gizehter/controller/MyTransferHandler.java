package aqbitig.gizehter.controller;

import aqbitig.lib.basic.T;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
        T.o();
        Transferable tranferable = new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                T.o();
                DataFlavor[] dataFlavor = new DataFlavor[1];
                return dataFlavor;
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                T.o();
                return true;
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                T.o();
                return null;
            }
        };
        return tranferable;
    }

    @Override
    public int getSourceActions(JComponent c) {
        T.o();
        return COPY_OR_MOVE;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        T.o("1p");
        support.setShowDropLocation(true);
        JTree tree = (JTree) support.getComponent();
        DefaultMutableTreeNode sourceNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

        return !(sourceNode == null || sourceNode.getAllowsChildren());
    }

    @Override
    public boolean importData(TransferSupport support) {
        T.o("1p");

        // GET TREE
        JTree tree = (JTree) support.getComponent();
        MyTreeModel myTreeModel = (MyTreeModel) tree.getModel();
        T.o("myTreeModel: " + myTreeModel);

        // GET DROP LOCATION
        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        T.o("drop location: " + dl);

        // GET SOURCE
        TreePath currentSelection = tree.getSelectionPath();
        DefaultMutableTreeNode sourceNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
        DefaultMutableTreeNode insertNode = new DefaultMutableTreeNode(sourceNode.getUserObject(), false);

        // GET SOURCE PARENT
        DefaultMutableTreeNode sourceNodeParent = (DefaultMutableTreeNode) sourceNode.getParent();
        int parentChildIndex = sourceNodeParent.getIndex(sourceNode);

        // GET TARGET NODE
        TreePath targetPath = dl.getPath();
        T.o("targetPath: " + targetPath);

        DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) targetPath.getLastPathComponent();
        T.o("target node: " + targetNode);

        // MOVE NODE
        myTreeModel.insertNodeInto(insertNode, targetNode, targetNode.getChildCount());

        // REMOVE SOURCE
        T.o("source node: " + sourceNode);
        T.o("source parent get child count: " + sourceNode.getParent().getChildCount());
        T.o("parent child index: " + parentChildIndex);
        sourceNodeParent.remove(parentChildIndex);

        // RELOAD
        myTreeModel.reload();
        tree.expandPath(targetPath);

        TreePath treePath = new TreePath(targetNode.getLastLeaf().getPath());
        T.o("treepath: " + treePath);
        tree.setSelectionPath(treePath);
        tree.scrollPathToVisible(treePath);
        return true;
    }

}
