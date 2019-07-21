package aqbitig.gizehter.view;

import aqbitig.lib.basic.T;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import aqbitig.gizehter.controller.bridge.InterfaceFileChooser;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class FileChooser extends javax.swing.JFileChooser {

    InterfaceFileChooser interfaceFileChooser;
    String mode;

    /**
     * Creates new form NewDatabase
     *
     * @param mode
     * @param interfaceFileChooser
     */
    public FileChooser(String mode, InterfaceFileChooser interfaceFileChooser) {
        this.interfaceFileChooser = interfaceFileChooser;
        this.mode = mode;
        initComponents();
        T.o(mode);

        setDialogTitle(mode);
        setFileFilter(new FileNameExtensionFilter("Database (*.qb)", "qb"));
    }

    private void initComponents() {

        setFileFilter(null);
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });
    }

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        T.o();
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
                    interfaceFileChooser.getFile(selectedFile);
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
                    interfaceFileChooser.getFile(selectedFile);
                }
            }

        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
            System.out.println(JFileChooser.CANCEL_SELECTION);
        }

    }

}
