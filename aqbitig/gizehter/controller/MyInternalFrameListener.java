/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.controller;

import aqbitig.lib.basic.T;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author aqdaycuklu
 */
public class MyInternalFrameListener implements InternalFrameListener {

    aqbitig.gizehter.view.InternalFrame internalFrame;

    public MyInternalFrameListener(aqbitig.gizehter.view.InternalFrame internalFrame) {
        this.internalFrame = internalFrame;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        T.o();
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        T.o();
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        T.o();
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        T.o();
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        T.o();
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        T.o();
        if (this.internalFrame.splitPane != null) {
            this.internalFrame.main.myMenuBar.setInternalFrame(this.internalFrame.splitPane);
        }
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        T.o();
    }

}
