package aqbitig.gizehter.model;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyAtomic {

    // ATOMIC DATA
    private String path, login, password, url, comment;
    private byte[] image;
    
    public static final char NODE_SEPARATOR = '→';

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
        this.path = name;
        this.login = "";
        this.password = "";
        this.url = "";
        this.comment = "";
        this.image = null;
    }

    public MyAtomic(String path, String login, String password, String url, String comment, byte[] image) {
        this.path = path;
        this.login = login;
        this.password = password;
        this.url = url;
        this.comment = comment;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {

        return this.path.substring(this.path.lastIndexOf(MyAtomic.NODE_SEPARATOR) + 1);
    }

}
