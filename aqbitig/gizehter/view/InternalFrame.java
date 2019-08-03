/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.gizehter.controller.bridge.InterfaceFileChooser;
import aqbitig.lib.basic.T;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author aqdaycuklu
 */
public class InternalFrame extends javax.swing.JInternalFrame {

    public java.io.File file;
    public String filePath;
    //public String mode = "";
    public String password = "";

    public aqbitig.gizehter.view.Main main;
    public aqbitig.gizehter.view.InternalFrame internalFrame = this;
    public aqbitig.gizehter.view.SplitPane splitPane;

    private final int xOffset = 25, yOffset = 25;

    public InternalFrame(aqbitig.gizehter.view.Main main, String mode) {
        super(mode + " " + main.desktopPane.getComponentCount(),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        nextPage(mode);

        this.main = main;
        addInternalFrameListener(new aqbitig.gizehter.controller.MyInternalFrameListener(this));

        setPosition();
        setVisible(true);
    }

    public void setPosition() {
        setSize((int) (main.getWidth() / 1.33333), (int) (main.getHeight() / 1.33333));
        int componentCount = main.desktopPane.getComponentCount();
        setLocation(xOffset * componentCount, yOffset * componentCount);
    }

    public void setPositionCenter() {
        //setSize((int) (main.getWidth() / 1.33333), (int) (main.getHeight() / 1.33333));
        setLocation(
                (int) ((main.getWidth() / 2)),
                (int) ((main.getHeight() / 4))
        );
    }

    public void nextPage(String mode) {
        T.o(mode);
        add(new aqbitig.gizehter.view.FileChooser(mode, new InterfaceFileChooser() {
            @Override
            public void getFile(File f) {
                T.o(mode);

                if (mode.equalsIgnoreCase("open")) {
                    aqbitig.lib.io.FileManager.backup(f.getPath());
                }
                aqbitig.gizehter.view.Password login = new aqbitig.gizehter.view.Password(internalFrame, mode, f);
                internalFrame.getContentPane().removeAll();
                internalFrame.add(login);
                internalFrame.pack();
            }

            @Override
            public void canceled() {

            }
        }));
        internalFrame.pack();

    }

    public File getFile() {
        return this.file;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMode() {
        //      return mode;
        return null;
    }

    public String getPassword() {
        return password;
    }

    public aqbitig.gizehter.view.SplitPane getSplitPane() {
        return this.splitPane;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setMode(String mode) {
//        this.mode = mode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSplitPane(aqbitig.gizehter.view.SplitPane splitPane) {
        this.splitPane = splitPane;
        this.main.myMenuBar.setInternalFrame(splitPane);
    }

}
