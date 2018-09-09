package aqbitig.gizehter.model;

import aqbitig.lib.T;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyMolecule {

    MyAtomic myAtomic = null;

    public MyMolecule(MyAtomic myAtomic) {
        this.myAtomic = myAtomic;
    }

    public void add(MyAtomic node) {
        if (myAtomic == null) {
            myAtomic = node;
        } else {
            MyAtomic myAtomicCopy = myAtomic;
            while (myAtomicCopy != null) {
                T.o(myAtomicCopy.getPath());
                if (myAtomicCopy.getPath().compareTo(node.getPath()) == 0) {
                    T.o(node);
                    break;
                } else if (myAtomicCopy.getPath().compareTo(node.getPath()) < 0) {
                    T.o("right");

                    if (myAtomicCopy.getDegree() == 1) {
                        if (myAtomicCopy.getRight().getPath().compareTo(node.getPath()) < 0) {

                        }
                    }

                    myAtomicCopy.setDegree(1);
                    if (myAtomicCopy.getRight() == null) {
                        T.o("set to right");
                        myAtomicCopy.setRight(node);
                        break;
                    } else {
                        T.o("go right");
                        myAtomicCopy = myAtomicCopy.getRight();
                    }
                } else if (myAtomicCopy.getPath().compareTo(node.getPath()) > 0) {
                    T.o("left");
                    myAtomicCopy.setDegree(-1);
                    if (myAtomicCopy.getLeft() == null) {
                        T.o("set to left");
                        myAtomicCopy.setLeft(node);
                        break;
                    } else {
                        T.o("go left");
                        myAtomicCopy = myAtomicCopy.getLeft();
                    }
                }
            }
        }

    }

    public MyAtomic get() {
        return this.myAtomic;
    }

    public void delete() {

    }

    public void set(MyMolecule node) {

    }

    public void o() {
        lor(myAtomic, 0, "c");
    }

    public void lor(MyAtomic myAtomic, int i, String p) {

        T.o(i + "\t" + p + "\t" + myAtomic.getDegree() + "\t" + myAtomic.getPath());
        if (myAtomic.getLeft() != null) {
            lor(myAtomic.getLeft(), ++i, "l");
        }
        if (myAtomic.getRight() != null) {
            lor(myAtomic.getRight(), ++i, "r");
        }

    }

}
