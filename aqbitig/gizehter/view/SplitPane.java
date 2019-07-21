package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.AbstractTree;
import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.basic.T;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class SplitPane extends javax.swing.JSplitPane {

    private final aqbitig.gizehter.controller.AbstractTree abstractTree;

    public SplitPane(String mode, java.io.File file, String password) {
        T.o("splitpane");

        abstractTree = new AbstractTree() {
            @Override
            public void leafSelected() {
                info.clear();

                TreePath path = tree.getSelectionPath();
                System.out.println(path.toString());

                TreeNode node = (TreeNode) path.getLastPathComponent();
                System.out.println(node);

                DefaultMutableTreeNode j = (DefaultMutableTreeNode) (path.getLastPathComponent());
                if (j.isLeaf() && !j.getAllowsChildren() && j.getUserObject() instanceof MyAtomic) {
                    MyAtomic m = (MyAtomic) j.getUserObject();
                    T.o(m.getLogin());
                    info.setNode(m);
                } else if (j.isLeaf() && !j.getAllowsChildren()) {
                    MyAtomic m = new MyAtomic();
                    j.setUserObject(m);
                    info.setNode(m);
                }

            }
        };
        abstractTree.setFile(file);
        abstractTree.setFilePath(file.getPath());
        abstractTree.setMode(mode);
        abstractTree.setPassword(password);
        abstractTree.setKey(password);
        initComponents();
        T.o("sp width: " + getSize().getWidth());
        setSize(500, 350);
        setDividerLocation(245);

    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDividerLocation(395);
        setDividerSize(10);
        setResizeWeight(0.5);
        setMinimumSize(new java.awt.Dimension(500, 350));
        setPreferredSize(new java.awt.Dimension(500, 350));

        tree = new aqbitig.gizehter.view.Tree(abstractTree);
        jScrollPane1.setViewportView(tree);

        setLeftComponent(jScrollPane1);

        info = new aqbitig.gizehter.view.Info(this);
        jScrollPane2.setViewportView(info);

        setRightComponent(jScrollPane2);

    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public javax.swing.JSplitPane getSplitPane() {
        return this;
    }

    public javax.swing.JScrollPane getjScrollPane() {
        return jScrollPane1;
    }

    // CUSTOM ATTRIBUTES
    private aqbitig.gizehter.view.Info info;
    private aqbitig.gizehter.view.Tree tree;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
}
