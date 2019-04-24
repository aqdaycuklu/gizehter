package aqbitig.lib.basic;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class T {

    public static void o() {
        String c = Thread.currentThread().getStackTrace()[2].getClassName();
        String m = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(c + "." + m + "()");
    }

    public static void o(boolean b) {
        for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            String c = Thread.currentThread().getStackTrace()[i].getClassName();
            String m = Thread.currentThread().getStackTrace()[i].getMethodName();
            System.out.println(c + "." + m + "()");
        }
    }

    public static void o(int i) {
        String c = Thread.currentThread().getStackTrace()[i].getClassName();
        String m = Thread.currentThread().getStackTrace()[i].getMethodName();
        System.out.println(c + "." + m + "(int) | " + i);
    }

    public static void o(String s) {
        String c = Thread.currentThread().getStackTrace()[2].getClassName();
        String m = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(c + "." + m + "(String) | " + s);
    }

    public static void o(Object o) {
        String c = Thread.currentThread().getStackTrace()[2].getClassName();
        String m = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(c + "." + m + "(object) | " + o);
    }

    public static void copyToClipboard(String input) {
        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
        clipboard.setContents(new java.awt.datatransfer.StringSelection(input), null);

    }

}
