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

    public static void o(Object o) {
        String c = Thread.currentThread().getStackTrace()[2].getClassName();
        String m = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(c + "." + m + "() | " + o);
    }
}
