package aqbitig.gizehter.view;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.basic.C;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class Info extends javax.swing.JPanel {

    aqbitig.gizehter.view.SplitPane splitPane;
    MyAtomic node;
    char passwordChar;
    

    /**
     * Creates new form input
     * @param splitPane
     */
    public Info(aqbitig.gizehter.view.SplitPane splitPane) {
        this.splitPane = splitPane;
        initComponents();
        this.passwordChar = jPasswordFieldPassword.getEchoChar();
    }

    public MyAtomic getNode() {
        return node;
    }

    public void setNode(MyAtomic node) {
        this.node = node;
        this.jTextFieldLogin.setText(node.getLogin());
        this.jPasswordFieldPassword.setText(node.getPassword());
        this.jTextFieldUrl.setText(node.getUrl());
        this.jTextPaneComment.setText(node.getComment());
        this.jLabel1.setText(node.getPath());
    }

    public void clear() {
        this.jTextFieldLogin.setText("");
        this.jPasswordFieldPassword.setText("");
        this.jTextFieldUrl.setText("");
        this.jTextPaneComment.setText("");
        this.jLabel1.setText("");
    }

    public void save() {
        this.node.setLogin(jTextFieldLogin.getText());
        this.node.setPassword(jPasswordFieldPassword.getText());
        this.node.setUrl(jTextFieldUrl.getText());
        this.node.setComment(jTextPaneComment.getText());
    }
    
    private void da(){
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldLogin = new javax.swing.JTextField();
        jLabelLogin = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelUrl = new javax.swing.JLabel();
        jTextFieldUrl = new javax.swing.JTextField();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneComment = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(230, 350));

        jTextFieldLogin.setText("");

        jLabelLogin.setText("Login");

        jLabelPassword.setText("Password");

        jLabelUrl.setText("Url");

        jTextFieldUrl.setText("");

        jPasswordFieldPassword.setText("");
        jPasswordFieldPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordFieldPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFieldPasswordFocusLost(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPaneComment);

        jLabel1.setText("");

        jButton1.setText("hold");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLogin)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelUrl))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldPassword)
                            .addComponent(jTextFieldUrl)))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLogin))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUrl)
                    .addComponent(jTextFieldUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordFocusGained
        // TODO add your handling code here:
        jPasswordFieldPassword.setEchoChar((char) 0);
    }//GEN-LAST:event_jPasswordFieldPasswordFocusGained

    private void jPasswordFieldPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordFocusLost
        // TODO add your handling code here:
        jPasswordFieldPassword.setEchoChar(this.passwordChar);
    }//GEN-LAST:event_jPasswordFieldPasswordFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUrl;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldUrl;
    private javax.swing.JTextPane jTextPaneComment;
    // End of variables declaration//GEN-END:variables

}
