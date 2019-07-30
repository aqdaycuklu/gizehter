/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.basic.T;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 *
 * @author aqdaycuklu
 */
public class Image extends javax.swing.JPanel {

    MyAtomic node;
    byte[] image;

    /**
     * Creates new form Image
     */
    public Image() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sunw05.jpg")));
        jLabel1.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        // process files
                        T.o(file.toString());
                        image = Files.readAllBytes(new File(file.toString()).toPath());
                        jLabel1.setIcon(new javax.swing.ImageIcon(image));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        jLabel1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        add(jLabel1);

        javax.swing.JButton jButton = new JButton();

        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel1.setIcon(null);
            }
        });
        jButton.setText("remove");
        add(jButton);

    }

    public void save() {
        this.node.setImage(image);
    }

    public MyAtomic getNode() {
        return node;
    }

    public void setNode(MyAtomic node) {
        this.node = node;
        jLabel1.setIcon(new javax.swing.ImageIcon(this.node.getImage()));

    }

    private javax.swing.JLabel jLabel1;
}
