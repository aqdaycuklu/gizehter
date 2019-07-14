/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import java.io.File;

/**
 *
 * @author aqdaycuklu
 */
public class InternalFrame extends javax.swing.JInternalFrame {

    public java.io.File file;
    public String filePath;
    public String mode = "";
    public String password = "";

    private static int openFrameCount = 0;
    private final int xOffset = 25, yOffset = 25;

    public InternalFrame(String title) {
        super(title + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        //...Create the GUI and put it in the window...
        //...Then set the window size or call pack...
        setSize(500, 350);

        //Set the window's location.
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }

    public File getFile() {
        return this.file;
    }

    public  String getFilePath() {
        return filePath;
    }

    public  String getMode() {
        return mode;
    }

    public  String getPassword() {
        return password;
    }

    public  void setFile(File file) {
        this.file = file;
    }

    public  void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public  void setMode(String mode) {
        this.mode = mode;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

}
