/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JSplitPane;

/**
 *
 * @author aqdaycuklu
 */
public class SplitPane extends javax.swing.JPanel {

    public SplitPane(aqbitig.gizehter.controller.MyTree myTree, aqbitig.gizehter.view.Info info) {

        // LEFT AND RIGHT COMPONENTS
        myTree.setMinimumSize(new Dimension(200, 300));
        myTree.setPreferredSize(new java.awt.Dimension(200, 300));
        info.setMinimumSize(new Dimension(200, 300));
        
        // CREATE SPLIT PANE
        javax.swing.JSplitPane jSplitPane = new javax.swing.JSplitPane(JSplitPane.HORIZONTAL_SPLIT, myTree, info);
        
        // SET LAYOUT
        jSplitPane.setResizeWeight(0.5);
        setLayout(new java.awt.GridLayout());
        add(jSplitPane);

    }

}
