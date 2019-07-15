/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.controller.implement;

import aqbitig.gizehter.controller.MyTreeModel;
import aqbitig.gizehter.view.Info;
import aqbitig.lib.basic.T;
import java.io.File;
import javax.swing.JOptionPane;
import aqbitig.gizehter.controller.bridge.InterfaceFileChooser;

/**
 *
 * @author aqdaycuklu
 */
public class ImplementsMenuBar implements aqbitig.gizehter.controller.bridge.InterfaceMenuBar {

    aqbitig.gizehter.view.Main main;
    aqbitig.gizehter.controller.MyTreeModel myTreeModel;
    javax.swing.JTree jTree;

    public ImplementsMenuBar(aqbitig.gizehter.view.Main main) {
        this.main = main;
    }

    @Override
    public void fileNew() {
        aqbitig.gizehter.view.InternalFrame internalFrame = new aqbitig.gizehter.view.InternalFrame("New qb file");
        internalFrame.add(new aqbitig.gizehter.view.FileChooser("new", new InterfaceFileChooser() {
            @Override
            public void getFile(File f) {
                T.o();

                /* LOGIN */
                aqbitig.gizehter.view.Login login = new aqbitig.gizehter.view.Login((char[] password) -> {
                    T.o(password);

                    /*  INTERNEL FRAME TITLE */
                    internalFrame.setTitle("Gizehter: " + f.getPath());

                    /* SPLIT PANE + TREE */
                    aqbitig.gizehter.view.SplitPane sp = new aqbitig.gizehter.view.SplitPane("new", f, new String(password));
                    sp.setVisible(true);
                    internalFrame.getContentPane().removeAll();
                    internalFrame.add(sp);
                    /* SPLIT PANE + TREE */

 /* GET TREEMODEL */
                    main.splitPane = sp;
                    jTree = sp.getjTree();
                    myTreeModel = (MyTreeModel) sp.getjTree().getModel();

                });
                login.setVisible(true);
                internalFrame.getContentPane().removeAll();
                internalFrame.add(login);
                main.pack();
                main.menuBar.menu(true);

                /* LOGIN. */
            }

            @Override
            public void canceled() {

            }
        })
        );
        internalFrame.setVisible(true);
        main.desktopPane.add(internalFrame);
    }

    @Override
    public void fileOpen() {
        /* INTERNAL FRAME */
        aqbitig.gizehter.view.InternalFrame internalFrame = new aqbitig.gizehter.view.InternalFrame("open qb file");

        /* FILE CHOOSER */
        aqbitig.gizehter.view.FileChooser fileChooser = new aqbitig.gizehter.view.FileChooser("open", new InterfaceFileChooser() {
            @Override
            public void getFile(File f) {
                aqbitig.lib.io.FileManager.backup(f.getPath());

                /* LOGIN */
                aqbitig.gizehter.view.Login login = new aqbitig.gizehter.view.Login((char[] password) -> {
                    T.o(password);
                    T.o(new String(password));
                    if (!aqbitig.lib.db.AqbSqlite.checkPassword(f.getPath(), new String(password))) {
                        JOptionPane.showMessageDialog(null, "Password not correct.");
                    } else {
                        /*  INTERNEL FRAME TITLE */
                        internalFrame.setTitle("Gizehter: " + f.getPath());

                        /* SPLIT PANE + TREE */
                        aqbitig.gizehter.view.SplitPane sp = new aqbitig.gizehter.view.SplitPane("open", f, new String(password));
                        ((MyTreeModel) sp.getjTree().getModel()).populateTree();
                        sp.setVisible(true);
                        internalFrame.getContentPane().removeAll();
                        internalFrame.add(sp);
                        /* SPLIT PANE + TREE */

 /* GET TREEMODEL */
                        main.splitPane = sp;
                        jTree = sp.getjTree();
                        myTreeModel = (MyTreeModel) sp.getjTree().getModel();

                    }
                });
                login.setVisible(true);
                internalFrame.getContentPane().removeAll();
                internalFrame.add(login);
                main.pack();
                main.menuBar.menu(true);
                /* LOGIN. */
            }

            @Override
            public void canceled() {

            }
        });
        internalFrame.add(fileChooser);
        internalFrame.setVisible(true);
        main.desktopPane.add(internalFrame);
        /* FILE CHOOSER */
    }

    @Override
    public void fileSave() {
        this.myTreeModel.save();
    }

    @Override
    public void fileClose() {
        this.myTreeModel.clear();
        ((Info) this.main.splitPane.getInfo()).clear();
        this.main.getContentPane().removeAll();
        this.main.menuBar.menu(false);
        this.main.pack();
    }

    @Override
    public void fileExit() {
        System.exit(0);
    }

    @Override
    public void treeAddBranch() {
        this.myTreeModel.addBranch(this.jTree.getSelectionPath());
    }

    @Override
    public void treeAddLeaf() {
        this.myTreeModel.addLeaf(this.jTree.getSelectionPath());
    }

    @Override
    public void treeRemove() {
        this.myTreeModel.remove(this.jTree.getSelectionPath());
    }

    @Override
    public void treeReload() {
        this.myTreeModel.reload();
    }

    @Override
    public void treeClear() {
        this.myTreeModel.clear();
    }

    @Override
    public void infoAbout() {
        JOptionPane.showMessageDialog(null, "AqBitig 2018\nhttps://www.aqbitig.de\naqdaycuklu@aqbitig.de");
    }

    @Override
    public void infoLicence() {
        String licenses = "Used third party libs/software:\n\nSQLite JDBC\nhttps://bitbucket.org/xerial/sqlite-jdbc/";
        JOptionPane.showMessageDialog(null, licenses);
    }

}
