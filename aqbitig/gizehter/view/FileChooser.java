/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.controller.FileChooserInterface;
import aqbitig.lib.T;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aqdaycuklu
 */
public class FileChooser extends javax.swing.JPanel {

    FileChooserInterface fileChooserInterface;
    String mode;

    /**
     * Creates new form NewDatabase
     */
    public FileChooser(String mode, FileChooserInterface fileChooserInterface) {
        this.fileChooserInterface = fileChooserInterface;
        this.mode = mode;
        initComponents();

        jFileChooser1.setDialogTitle(mode);
        jFileChooser1.setFileFilter(new FileNameExtensionFilter("Database (*.qb)", "qb"));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        jFileChooser1.setFileFilter(null);
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        JFileChooser theFileChooser = (JFileChooser) evt.getSource();
        String command = evt.getActionCommand();
        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
            File selectedFile = theFileChooser.getSelectedFile();

            if ("open".equals(mode)) {
                T.o("mode: open");
                if (!selectedFile.exists()) {
                    T.o("mode: open: file not exists");
                    JOptionPane.showMessageDialog(null, "File not exists.");
                } else {
                    fileChooserInterface.getFile(selectedFile);
                }
            } else if ("new".equals(mode)) {
                if (selectedFile.exists()) {
                    T.o("mode: open: file already exists");
                    JOptionPane.showMessageDialog(null, "File already exists.");
                } else {
                    String filePath = selectedFile.getPath();
                    if (!filePath.toLowerCase().endsWith(".qb")) {
                        selectedFile = new File(filePath + ".qb");
                    }
                    fileChooserInterface.getFile(selectedFile);
                }
            }

        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
            System.out.println(JFileChooser.CANCEL_SELECTION);
        }


    }//GEN-LAST:event_jFileChooser1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
}
