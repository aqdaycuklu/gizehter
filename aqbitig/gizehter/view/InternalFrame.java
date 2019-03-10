/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.view;

import aqbitig.gizehter.controller.InterfaceFileChooser;
import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.basic.T;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author aqdaycuklu
 */
public class InternalFrame extends javax.swing.JInternalFrame {

    // CONFIG
    private java.io.File file;
    private String mode = "";
    private String password = "";

    // PANEL
    private aqbitig.gizehter.view.SplitPane splitPane;
    private aqbitig.gizehter.view.MyTree myTree;
    private aqbitig.gizehter.controller.MyTreeModel myTreeModel;
    private aqbitig.gizehter.view.Info info;

    // DATABASE
    private aqbitig.lib.db.AqbSqlite aqbSqlite;

    private static int openFrameCount = 1;
    private final int xOffset = 30, yOffset = 30;

    private String devDir = "/home/aqdaycuklu/Documents/test.qb";
    private String devPassword = "seda";

    public InternalFrame(String mode) {
        super(mode + " qb " + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        //...create the gui and put it in the window...
        //...then set the window size or call pack...
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new java.awt.Dimension(500, 300));

        // set the window's location.
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

        // always start file chooser
        this.setConfigMode(mode);
        //screenFileChooser();

        setConfigFile(new java.io.File("/home/aqdaycuklu/Documents/test.qb"));
        setConfigPassword(new String("seda"));
        screenSplitPane();
    }

    private void screenFileChooser() {
        add(new aqbitig.gizehter.view.FileChooser(this.mode, new InterfaceFileChooser() {
            @Override
            public void getFile(File f) {
                setConfigFile(f);
                screenLogin();
            }

            @Override
            public void canceled() {

            }
        }));
        pack();
    }

    private void screenLogin() {

        /* LOGIN */
        aqbitig.gizehter.view.Login login = new aqbitig.gizehter.view.Login((char[] password) -> {
            T.o(password);
            setConfigPassword(new String(password));
            if (this.mode.equalsIgnoreCase("open") && !aqbitig.lib.db.AqbSqlite.checkPassword(getFile().getPath(), this.password)) {
                JOptionPane.showMessageDialog(null, "Check password!");
            } else if (this.mode.equalsIgnoreCase("new") && !aqbitig.lib.db.AqbSqlite.setKey(getFile(), this.password)) {
                JOptionPane.showMessageDialog(null, "Could not create database!");
            } else {

                /*  INTERNEL FRAME TITLE */
                setTitle("Gizehter: " + getConfigFilePath());

                /* SPLIT PANE */
                screenSplitPane();
            }
        });
        login.setVisible(true);
        getContentPane().removeAll();
        add(login);
        pack();
        /* LOGIN. */
    }

    private void screenSplitPane() {

        //aqbitig.lib.io.FileManager.backup(getFile().getPath());

        this.info = new aqbitig.gizehter.view.Info((node) -> {
            myTreeModel.saveSelected(node);
        });

        this.myTree = new aqbitig.gizehter.view.MyTree();
        this.myTree.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    aqbitig.gizehter.view.menu.MenuTree popupMenu = new aqbitig.gizehter.view.menu.MenuTree((javax.swing.JTree) e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        this.myTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                T.o(e.getPath());

                info.clear();
                info.deactivate();

                TreePath path = e.getPath();//myTree.getSelectionPath();
                T.o(path.toString());

                TreeNode node = (TreeNode) path.getLastPathComponent();
                T.o(node);

                DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
                if (defaultMutableTreeNode.isLeaf() && !defaultMutableTreeNode.getAllowsChildren() && defaultMutableTreeNode.getUserObject() instanceof MyAtomic) {
                    MyAtomic m = (MyAtomic) defaultMutableTreeNode.getUserObject();
                    T.o(m.getLogin());
                    info.setNode(m);
                    info.activate();
                } else if (defaultMutableTreeNode.isLeaf() && !defaultMutableTreeNode.getAllowsChildren()) {
                    MyAtomic m = new MyAtomic();
                    defaultMutableTreeNode.setUserObject(m);
                    info.setNode(m);
                    info.activate();
                }
            }
        });

        // DATABASE
        aqbitig.lib.db.AqbSqlite aqbSqlite = new aqbitig.lib.db.AqbSqlite(this.file, this.password);

        // TREE MODEL
        MyTreeModel myTreeModel = new MyTreeModel(aqbSqlite);
        myTreeModel.addTreeModelListener(new TreeModelListener() {
            @Override
            public void treeNodesChanged(TreeModelEvent e) {
                T.o();
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e) {
                T.o();
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e) {
                T.o();
            }

            @Override
            public void treeStructureChanged(TreeModelEvent e) {
                T.o();
            }
        });
        this.myTree.setModel(myTreeModel);

        this.myTreeModel = (aqbitig.gizehter.controller.MyTreeModel) this.myTree.getModel();
        this.splitPane = new aqbitig.gizehter.view.SplitPane(this.myTree, this.info);

        // set menu bar
        setJMenuBar(new aqbitig.gizehter.view.menu.MenuFrameInternal(this.myTree));

        //((MyTreeModel) this.splitPane.getjTree().getModel()).populateTree();
        this.splitPane.setVisible(true);
        getContentPane().removeAll();

        add(this.splitPane);
        pack();
    }

    public File getFile() {
        return this.file;
    }

    public String getConfigFilePath() {
        return this.file.getPath();
    }

    public String getConfigMode() {
        return mode;
    }

    public String getConfigPassword() {
        return password;
    }

    public MyTreeModel getTreeModel() {
        return null;//(aqbitig.gizehter.controller.MyTreeModel) this.splitPane.getjTree().getModel();
    }

    private void setConfigFile(File file) {
        this.file = file;
    }

    private void setConfigMode(String mode) {
        this.mode = mode;
    }

    private void setConfigPassword(String password) {
        this.password = password;
    }

}
