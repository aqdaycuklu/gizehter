package aqbitig.gizehter.view;

import aqbitig.controller.MyTreeModel;
import aqbitig.gizehter.model.MyAtomic;
import aqbitig.io.FileManager;
import aqbitig.lib.T;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import aqbitig.controller.InterfaceTree;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class Layout extends javax.swing.JFrame {

    private final InterfaceTree iface;

    /**
     * Creates new form NewJFrame
     */
    public Layout() {

        FileManager.backup("gizehter.db");

        iface = new InterfaceTree() {
            @Override
            public void leafSelected() {
                info1.claer();
                info1.deactivate();

                TreePath path = jTree.getSelectionPath();
                System.out.println(path.toString());

                TreeNode node = (TreeNode) path.getLastPathComponent();
                System.out.println(node);

                DefaultMutableTreeNode j = (DefaultMutableTreeNode) (path.getLastPathComponent());
                if (j.isLeaf() && !j.getAllowsChildren() && j.getUserObject() instanceof MyAtomic) {
                    MyAtomic m = (MyAtomic) j.getUserObject();
                    T.o(m.login);
                    info1.setNode(m);
                    info1.activate();
                } else if (j.isLeaf() && !j.getAllowsChildren()) {
                    MyAtomic m = new MyAtomic();
                    j.setUserObject(m);
                    info1.setNode(m);
                    info1.activate();
                }

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

        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new aqbitig.controller.MyTree(iface);
        info1 = new aqbitig.gizehter.view.Info();
        jMenuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        reload = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        clear = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        addBranch = new javax.swing.JMenuItem();
        addLeaf = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        remove = new javax.swing.JMenuItem();
        info = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(375);
        jSplitPane2.setDividerSize(10);

        jScrollPane1.setViewportView(jTree);

        jSplitPane2.setLeftComponent(jScrollPane1);
        jSplitPane2.setRightComponent(info1);

        file.setText("File");

        reload.setText("Reload");
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });
        file.add(reload);
        file.add(jSeparator3);

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        file.add(clear);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        file.add(save);
        file.add(jSeparator1);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        file.add(exit);

        jMenuBar.add(file);

        edit.setText("Edit");

        addBranch.setText("Add Branch");
        addBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBranchActionPerformed(evt);
            }
        });
        edit.add(addBranch);

        addLeaf.setText("Add Leaf");
        addLeaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLeafActionPerformed(evt);
            }
        });
        edit.add(addLeaf);
        edit.add(jSeparator2);

        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        edit.add(remove);

        jMenuBar.add(edit);

        info.setText("Info");
        jMenuBar.add(info);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        ((MyTreeModel) jTree.getModel()).clear();
    }//GEN-LAST:event_clearActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        ((MyTreeModel) jTree.getModel()).save();
    }//GEN-LAST:event_saveActionPerformed

    private void addBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBranchActionPerformed
        // TODO add your handling code here:
        ((MyTreeModel) jTree.getModel()).addBranch(jTree.getSelectionPath());
    }//GEN-LAST:event_addBranchActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // TODO add your handling code here:
        ((MyTreeModel) jTree.getModel()).remove(jTree.getSelectionPath());
    }//GEN-LAST:event_removeActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
        // TODO add your handling code here:
        ((MyTreeModel) jTree.getModel()).reload();
    }//GEN-LAST:event_reloadActionPerformed

    private void addLeafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLeafActionPerformed
        // TODO add your handling code here:
        ((MyTreeModel) jTree.getModel()).addLeaf(jTree.getSelectionPath());

    }//GEN-LAST:event_addLeafActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Layout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addBranch;
    private javax.swing.JMenuItem addLeaf;
    private javax.swing.JMenuItem clear;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu file;
    private javax.swing.JMenu info;
    private aqbitig.gizehter.view.Info info1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTree jTree;
    private javax.swing.JMenuItem reload;
    private javax.swing.JMenuItem remove;
    private javax.swing.JMenuItem save;
    // End of variables declaration//GEN-END:variables
}
