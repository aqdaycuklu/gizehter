/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.controller.bridge;

/**
 *
 * @author aqdaycuklu
 */
public interface InterfaceMenuBar {

    public void fileNew();
    public void fileOpen();
    public void fileSave();
    public void fileClose();
    public void fileExit();

    public void treeAddBranch();
    public void treeAddLeaf();
    public void treeRemove();
    public void treeReload();
    public void treeClear();
    
    public void infoAbout();
    public void infoLicence();

}
