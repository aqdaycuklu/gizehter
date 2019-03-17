package aqbitig.gizehter.model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyAtomic {

    // STATUS
    private boolean newNode;

    // ATOMIC DATA
    private String path, oldPath, login, password, url, comment;

    // INDEX
    private int index, level;

    // SHOW
    boolean leaf;

    // FOR AVL
    MyAtomic left, right;
    int degree = 0;

    public MyAtomic() {
        this.path = "";
        this.login = "";
        this.password = "";
        this.url = "";
        this.comment = "";
    }

    public MyAtomic(String name) {
        this.newNode = true;
        this.path = this.oldPath = name;
        this.login = "";
        this.password = "";
        this.url = "";
        this.comment = "";
    }

    public MyAtomic(String path, String login, String password, String url, String comment) {
        this.newNode = false;
        this.path = this.oldPath = path;
        this.login = login;
        this.password = password;
        this.url = url;
        this.comment = comment;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOldPath() {
        return this.oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public void setNewPath(DefaultMutableTreeNode parent) {
        
        
//        this.path = parentPath + '.' + this.path.substring(this.path.lastIndexOf('.') + 1);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIndex() {
        return index;
    }

    public int getLevel() {
        return level;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isNewNode() {
        return newNode;
    }

    public void setNewNode(boolean newNode) {
        this.newNode = newNode;
    }

    public MyAtomic getLeft() {
        return left;
    }

    public void setLeft(MyAtomic left) {
        this.left = left;
    }

    public MyAtomic getRight() {
        return right;
    }

    public void setRight(MyAtomic right) {
        this.right = right;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree += degree;
    }

    @Override
    public String toString() {

        return this.path.substring(this.path.lastIndexOf(".") + 1);
    }

}
