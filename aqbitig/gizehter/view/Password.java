package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.MyTreeModel;
import java.util.Arrays;
import javax.swing.JOptionPane;
import aqbitig.gizehter.controller.bridge.InterfaceLogin;
import aqbitig.lib.basic.T;
import java.awt.Component;
import java.io.File;
import javax.swing.BoxLayout;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class Password extends javax.swing.JPanel {

    aqbitig.gizehter.view.InternalFrame internalFrame;

    File f;
    String mode;

    /**
     * Creates new form InterfaceLogin
     *
     * @param internalFrame
     * @param mode
     * @param f
     */
    public Password(aqbitig.gizehter.view.InternalFrame internalFrame, String mode, File f) {
        this.internalFrame = internalFrame;
        this.f = f;
        this.mode = mode;
        T.o(mode);
        initComponents();

        if (this.mode == "open") {
            jPasswordField2.setVisible(false);
        }

        setVisible(true);

        internalFrame.setPositionCenter();

    }

    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButtonPassword = new javax.swing.JButton();

        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });

        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusLost(evt);
            }
        });
        jPasswordField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField2MouseClicked(evt);
            }
        });

        jButtonPassword.setText("Password");
        jButtonPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasswordActionPerformed(evt);
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jPasswordField1.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jPasswordField1);

        jPasswordField2.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jPasswordField2);

        jButtonPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jButtonPassword);
    }

    private void nextPage(String password) {

        if (this.mode == "open") {
            if (!aqbitig.lib.db.AqbSqlite.checkPassword(f.getPath(), password)) {
                JOptionPane.showMessageDialog(null, "Password not correct.");
                return;
            }
        }

        /*  INTERNEL FRAME TITLE */
        internalFrame.setTitle("Gizehter: " + f.getPath());

        /* SPLIT PANE + TREE */
        aqbitig.gizehter.view.SplitPane sp = new aqbitig.gizehter.view.SplitPane(this.mode, f, password);
        sp.setVisible(true);
        internalFrame.getContentPane().removeAll();
        internalFrame.add(sp);
        /* SPLIT PANE + TREE */

        ((MyTreeModel) sp.getTree().getModel()).populateTree();

        internalFrame.setSplitPane(sp);
        internalFrame.setPosition();

    }

    private void jButtonPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasswordActionPerformed
        // TODO add your handling code here:

        if (this.mode == "open") {
            nextPage(new String(jPasswordField1.getPassword()));
            return;
        }

        if (Arrays.toString(jPasswordField1.getPassword()).equals(Arrays.toString(jPasswordField2.getPassword()))) {
            nextPage(new String(jPasswordField1.getPassword()));
        } else {
            JOptionPane.showMessageDialog(null, "Password incorrect!");
        }
    }//GEN-LAST:event_jButtonPasswordActionPerformed

    private void jPasswordField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            jPasswordField2.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_jPasswordField2MouseClicked

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        // TODO add your handling code here:
        jPasswordField2.setEchoChar('*');
    }//GEN-LAST:event_jPasswordField2FocusLost

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            jPasswordField1.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        // TODO add your handling code here:
        jPasswordField1.setEchoChar('*');
    }//GEN-LAST:event_jPasswordField1FocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPassword;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables
}
