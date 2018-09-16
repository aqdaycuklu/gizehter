/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.controller.Interface;
import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.T;
import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author aqdaycuklu
 */
public class SplitPane extends javax.swing.JPanel {

    private final Interface iface;

    /**
     * Creates new form Layout2
     */
    public SplitPane() {
        T.o("splitpane");
        iface = () -> {
            info.claer();
            info.deactivate();

            TreePath path = jTree.getSelectionPath();
            System.out.println(path.toString());

            TreeNode node = (TreeNode) path.getLastPathComponent();
            System.out.println(node);

            DefaultMutableTreeNode j = (DefaultMutableTreeNode) (path.getLastPathComponent());
            if (j.isLeaf() && !j.getAllowsChildren() && j.getUserObject() instanceof MyAtomic) {
                MyAtomic m = (MyAtomic) j.getUserObject();
                T.o(m.login);
                info.setNode(m);
                info.activate();
            } else if (j.isLeaf() && !j.getAllowsChildren()) {
                MyAtomic m = new MyAtomic();
                j.setUserObject(m);
                info.setNode(m);
                info.activate();
            }
        };
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        info = new aqbitig.gizehter.view.Info();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new aqbitig.controller.MyTree(iface);

        jSplitPane1.setDividerLocation(395);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setRightComponent(info);

        jScrollPane1.setViewportView(jTree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public JTree getjTree() {
        return jTree;
    }

    public void setjTree1(JTree jTree) {
        this.jTree = jTree;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private aqbitig.gizehter.view.Info info;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables
}
