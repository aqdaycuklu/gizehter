/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aqbitig.gizehter.controller;

import java.io.File;

/**
 *
 * @author aqdaycuklu
 */
public interface InterfaceFileChooser {

    /**
     *
     * @return
     */
    public void getFile(File fileName);
    public void canceled();
}
