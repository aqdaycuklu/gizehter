/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.basic.T;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author aqdaycuklu
 */
public class Image extends javax.swing.JPanel {

    MyAtomic node;
    byte[] image;

    JButton hold;

    /**
     * Creates new form Image
     */
    public Image(JButton jButton) {
        this.hold = jButton;
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        jLabel1 = new javax.swing.JLabel();
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setVerticalAlignment(JLabel.CENTER);
        jLabel1.setText("Drop image here");
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

        jLabel1.setMinimumSize(new Dimension(50, 30));
        jLabel1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        add(jLabel1, BorderLayout.CENTER);

        javax.swing.JButton jButton = new JButton();

        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel1.setIcon(null);
                node.setImage(null);
                hold.setForeground(java.awt.Color.RED);

            }
        });
        jButton.setText("Remove image");
        add(jButton, BorderLayout.PAGE_END);

    }

    public void clear() {
        this.image = null;
        jLabel1.setIcon(null);
        jLabel1.setText("Drop icon here...");
    }

    public void save() {
        this.node.setImage(image);
    }

    public MyAtomic getNode() {
        return node;
    }

    public void setNode(MyAtomic node) {
        this.node = node;
        if (this.node.getImage() == null) {
            clear();
        } else {
            this.image = node.getImage();
            javax.swing.ImageIcon imageIcon = new ImageIcon(this.image);
            jLabel1.setIcon(new ImageIcon(getScaledImage(imageIcon.getImage(), 250, 250)));
            jLabel1.setText(null);
        }
    }

    private java.awt.Image getScaledImage(java.awt.Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    private javax.swing.JLabel jLabel1;
}
