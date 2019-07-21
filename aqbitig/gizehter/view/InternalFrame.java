/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.gizehter.controller.bridge.InterfaceFileChooser;
import aqbitig.lib.basic.T;
import java.io.File;
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
    public String mode = "";
    public String password = "";

    public aqbitig.gizehter.view.Main main;
    public aqbitig.gizehter.view.InternalFrame internalFrame = this;
    public aqbitig.gizehter.view.SplitPane splitPane;

    private static int openFrameCount = 0;
    private final int xOffset = 25, yOffset = 25;

    public InternalFrame(aqbitig.gizehter.view.Main main, String title) {
        super(title + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        if (title == "new") {
            title = "New qb file";
            nextPageNew();
        } else if (title == "open") {
            title = "Open qb file";
            nextPageOpen();
        }

        //...Create the GUI and put it in the window...
        //...Then set the window size or call pack...
        setSize(500, 350);

        //Set the window's location.
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

        this.main = main;
        addInternalFrameListener(new aqbitig.gizehter.controller.MyInternalFrameListener(this));
    }

    public void nextPageNew() {

        add(new aqbitig.gizehter.view.FileChooser("new", new InterfaceFileChooser() {
            @Override
            public void getFile(File f) {
                T.o();

                aqbitig.gizehter.view.Login login = new aqbitig.gizehter.view.Login(internalFrame, "new", f);
                login.setVisible(true);
                internalFrame.getContentPane().removeAll();
                internalFrame.add(login);
            }

            @Override
            public void canceled() {

            }
        })
        );
        setVisible(true);
        //     main.desktopPane.add(internalFrame);
    }

    public void nextPageOpen() {
        add(new aqbitig.gizehter.view.FileChooser("open", new InterfaceFileChooser() {
            @Override
            public void getFile(File f) {
                T.o();

                aqbitig.lib.io.FileManager.backup(f.getPath());

                aqbitig.gizehter.view.Login login = new aqbitig.gizehter.view.Login(internalFrame, "open", f);
                login.setVisible(true);
                internalFrame.getContentPane().removeAll();
                internalFrame.add(login);
                internalFrame.pack();
            }

            @Override
            public void canceled() {

            }
        })
        );
        setVisible(true);
    }

    public File getFile() {
        return this.file;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMode() {
        return mode;
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
        this.mode = mode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSplitPane(aqbitig.gizehter.view.SplitPane splitPane) {
        this.splitPane = splitPane;
        this.main.myMenuBar.setInternalFrame(splitPane);
    }

}
