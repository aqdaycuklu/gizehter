package aqbitig.gizehter.controller;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.gizehter.view.Main;
import aqbitig.lib.basic.C;
import aqbitig.lib.basic.T;
import aqbitig.lib.db.AqbSqlite;
import java.io.File;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class MyTreeModel implements TreeModel {

    protected DefaultMutableTreeNode root;
    protected EventListenerList listenerList = new EventListenerList();
    protected boolean asksAllowsChildren;
    public File file;

    /* constructor */
    public MyTreeModel(DefaultMutableTreeNode root) {
        this(root, false);
    }

    public MyTreeModel() {
        this.root = new DefaultMutableTreeNode("root");
    }

    public MyTreeModel(DefaultMutableTreeNode root, boolean asksAllowsChildren) {
        super();
        T.o();
        this.root = root;
        this.asksAllowsChildren = asksAllowsChildren;
    }

    /* constructor. */

 /* class attributes */
    public void setRoot(DefaultMutableTreeNode root) {
        T.o();
        Object oldRoot = this.root;
        this.root = root;
        if (root == null && oldRoot != null) {
            fireTreeStructureChanged(this, null);
        } else {
            nodeStructureChanged(root);
        }
    }

    public void setAsksAllowsChildren(boolean newValue) {
        T.o();
        asksAllowsChildren = newValue;
    }

    public boolean asksAllowsChildren() {
        T.o();
        return asksAllowsChildren;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /* class attributes. */

 /* override methods */
    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeNode) parent).getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeNode) parent).getChildCount();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent == null || child == null) {
            return -1;
        }
        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    @Override
    public boolean isLeaf(Object node) {
        if (asksAllowsChildren) {
            return !((TreeNode) node).getAllowsChildren();
        }
        return ((TreeNode) node).isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        T.o(true);
        T.o(path);
        T.o(newValue);
        T.o();

        DefaultMutableTreeNode aNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) aNode.getParent();

        if (!canRename(parent, newValue.toString())) {
            return;
        }

        if (aNode.getAllowsChildren()) {
            aNode.setUserObject(newValue);
        } else {
            MyAtomic myAtomic = (MyAtomic) aNode.getUserObject();
            myAtomic.setPath(newValue.toString());
            aNode.setUserObject(myAtomic);
        }

    }

    private boolean canRename(DefaultMutableTreeNode parent, String childName) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (child.toString().equalsIgnoreCase(childName)) {
                T.o("node already exists!");
                return false;
            }
        }
        return true;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        T.o();
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        T.o();
        listenerList.remove(TreeModelListener.class, l);
    }

    /* override methods. */

 /* methods */
    public void reload() {
        reload(root);
    }

    public void reload(TreeNode node) {
        T.o();
        if (node != null) {
            fireTreeStructureChanged(this, getPathToRoot(node), null, null);
        }
    }

    public TreeNode[] getPathToRoot(TreeNode aNode) {
        T.o();
        return getPathToRoot(aNode, 0);
    }

    protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
        T.o();
        TreeNode[] retNodes;

        if (aNode == null) {
            if (depth == 0) {
                return null;
            } else {
                retNodes = new TreeNode[depth];
            }
        } else {
            depth++;
            if (aNode == root) {
                retNodes = new TreeNode[depth];
            } else {
                retNodes = getPathToRoot(aNode.getParent(), depth);
            }
            retNodes[retNodes.length - depth] = aNode;
        }
        return retNodes;
    }

    protected void fireTreeStructureChanged(Object source, Object[] path,
            int[] childIndices,
            Object[] children) {
        T.o();
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null) {
                    e = new TreeModelEvent(source, path,
                            childIndices, children);
                }
                ((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
            }
        }
    }

    private void fireTreeStructureChanged(Object source, TreePath path) {
        T.o();
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null) {
                    e = new TreeModelEvent(source, path);
                }
                ((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
            }
        }
    }

    //-------
    public void removeNodeFromParent(MutableTreeNode node) {
        T.o();
        MutableTreeNode parent = (MutableTreeNode) node.getParent();

        if (parent == null) {
            throw new IllegalArgumentException("node does not have a parent.");
        }

        int[] childIndex = new int[1];
        Object[] removedArray = new Object[1];

        childIndex[0] = parent.getIndex(node);
        parent.remove(childIndex[0]);
        removedArray[0] = node;
        nodesWereRemoved(parent, childIndex, removedArray);
    }

    public void nodesWereInserted(TreeNode node, int[] childIndices) {
        T.o();
        if (listenerList != null && node != null && childIndices != null
                && childIndices.length > 0) {
            int cCount = childIndices.length;
            Object[] newChildren = new Object[cCount];

            for (int counter = 0; counter < cCount; counter++) {
                newChildren[counter] = node.getChildAt(childIndices[counter]);
            }
            fireTreeNodesInserted(this, getPathToRoot(node), childIndices,
                    newChildren);
        }
    }

    public void nodesWereRemoved(TreeNode node, int[] childIndices,
            Object[] removedChildren) {
        T.o();
        if (node != null && childIndices != null) {
            fireTreeNodesRemoved(this, getPathToRoot(node), childIndices,
                    removedChildren);
        }
    }

    protected void fireTreeNodesInserted(Object source, Object[] path,
            int[] childIndices,
            Object[] children) {
        T.o();
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null) {
                    e = new TreeModelEvent(source, path,
                            childIndices, children);
                }
                ((TreeModelListener) listeners[i + 1]).treeNodesInserted(e);
            }
        }
    }

    protected void fireTreeNodesChanged(Object source, Object[] path,
            int[] childIndices,
            Object[] children) {
        T.o();
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null) {
                    e = new TreeModelEvent(source, path,
                            childIndices, children);
                }
                ((TreeModelListener) listeners[i + 1]).treeNodesChanged(e);
            }
        }
    }

    protected void fireTreeNodesRemoved(Object source, Object[] path,
            int[] childIndices,
            Object[] children) {
        T.o();
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null) {
                    e = new TreeModelEvent(source, path,
                            childIndices, children);
                }
                ((TreeModelListener) listeners[i + 1]).treeNodesRemoved(e);
            }
        }
    }

    //----------
    public void insertNodeInto(MutableTreeNode newChild,
            MutableTreeNode parent, int index) {
        parent.insert(newChild, index);

        int[] newIndexs = new int[1];

        newIndexs[0] = index;
        nodesWereInserted(parent, newIndexs);
    }

    public void nodeChanged(TreeNode node) {
        T.o();
        if (listenerList != null && node != null) {
            TreeNode parent = node.getParent();

            if (parent != null) {
                int anIndex = parent.getIndex(node);
                if (anIndex != -1) {
                    int[] cIndexs = new int[1];

                    cIndexs[0] = anIndex;
                    nodesChanged(parent, cIndexs);
                }
            } else if (node == getRoot()) {
                nodesChanged(node, null);
            }
        }
    }

    public void nodesChanged(TreeNode node, int[] childIndices) {
        T.o();
        if (node != null) {
            if (childIndices != null) {
                int cCount = childIndices.length;

                if (cCount > 0) {
                    Object[] cChildren = new Object[cCount];

                    for (int counter = 0; counter < cCount; counter++) {
                        cChildren[counter] = node.getChildAt(childIndices[counter]);
                    }
                    fireTreeNodesChanged(this, getPathToRoot(node),
                            childIndices, cChildren);
                }
            } else if (node == getRoot()) {
                fireTreeNodesChanged(this, getPathToRoot(node), null, null);
            }
        }
    }

    public void nodeStructureChanged(TreeNode node) {
        T.o();
        if (node != null) {
            fireTreeStructureChanged(this, getPathToRoot(node), null, null);
        }
    }


    /* methods. */
    /**
     * Remove all nodes except the root node.
     */
    public void clear() {
        T.o();
        this.root.removeAllChildren();
        this.reload();
    }

    public void save() {
        T.o("DynamicTree: save()");
        AqbSqlite.truncate();
        walk(this, root);
    }

    /**
     * Remove the currently selected node.
     *
     * @param currentSelection
     */
    public void remove(TreePath currentSelection) {
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
            if (parent != null) {
                this.removeNodeFromParent(currentNode);
            }
        }
    }

    /**
     * Add child to the currently selected node.
     *
     * @param parentPath
     * @return
     */
    public DefaultMutableTreeNode addLeaf(TreePath parentPath) {
        DefaultMutableTreeNode parentNode;
        MyAtomic myAtomic = new MyAtomic("project", "login", "password", "url", "comment");

        if (parentPath == null) {
            parentNode = root;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }

        String leafName = "leaf";
        String suffix = "01";
        boolean continueWhile = true;
        while (continueWhile) {
            boolean found = false;
            T.o("child count: " + parentNode.getChildCount());
            for (int i = 0; i < parentNode.getChildCount(); i++) {
                DefaultMutableTreeNode name = (DefaultMutableTreeNode) parentNode.getChildAt(i);
                T.o("user object: " + name.toString());
                if (leafName.equalsIgnoreCase(name.toString())) {
                    found = true;
                    String ii = leafName.substring(leafName.length() - 2);
                    T.o(ii);
                    boolean iii = ii.matches("\\d+");
                    T.o(iii);
                    int iiii = 0;
                    if (iii) {
                        iiii = Integer.parseInt(ii) + 1;
                        if (iiii < 10) {
                            suffix = "0" + iiii;
                        } else {
                            suffix = "" + iiii;
                        }
                    } else {
                        suffix = "af01";
                    }
                    T.o(suffix);

                    leafName = leafName.substring(0, leafName.length() - 2) + suffix;
                    T.o("branchName: " + leafName);
                }
            }
            T.o("found: " + found);
            if (!found) {
                continueWhile = false;
            }

        }

        MyAtomic leaf = new MyAtomic(leafName);
        return addObject(parentNode, leaf, false, true);
    }

    /**
     * Add child to the currently selected node.
     *
     * @param parentPath
     * @return
     */
    public DefaultMutableTreeNode addBranch(TreePath parentPath) {
        DefaultMutableTreeNode parentNode;

        if (parentPath == null) {
            parentNode = root;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }

        String branchName = "branch";
        String suffix = "01";
        boolean continueWhile = true;
        while (continueWhile) {
            boolean found = false;
            T.o("child count: " + parentNode.getChildCount());
            for (int i = 0; i < parentNode.getChildCount(); i++) {
                DefaultMutableTreeNode name = (DefaultMutableTreeNode) parentNode.getChildAt(i);
                T.o("user object: " + name.toString());
                if (branchName.equalsIgnoreCase(name.toString())) {
                    found = true;
                    String ii = branchName.substring(branchName.length() - 2);
                    T.o(ii);
                    boolean iii = ii.matches("\\d+");
                    T.o(iii);
                    int iiii = 0;
                    if (iii) {
                        iiii = Integer.parseInt(ii) + 1;
                        if (iiii < 10) {
                            suffix = "0" + iiii;
                        } else {
                            suffix = "" + iiii;
                        }
                    } else {
                        suffix = "ch01";
                    }
                    T.o(suffix);

                    branchName = branchName.substring(0, branchName.length() - 2) + suffix;
                    T.o("branchName: " + branchName);
                }
            }
            T.o("found: " + found);
            if (!found) {
                continueWhile = false;
            }

        }

        return addObject(parentNode, branchName, true, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
        return addObject(parent, child, true, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean alloowsChildren, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child, alloowsChildren);

        if (parent == null) {
            parent = root;
        }

        // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        this.insertNodeInto(childNode, parent, parent.getChildCount());
        if (!alloowsChildren) {
            ((MyAtomic) child).setPath(getPath(childNode));
        } else {
            this.setAsksAllowsChildren(true);
        }

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            //    jTree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    public void populateTree() {
        List<MyAtomic> myAtomicSet = AqbSqlite.load();
        for (MyAtomic myAtomic : myAtomicSet) {
            T.o("\n");
            T.o("myAtomic.getpath() " + myAtomic.getPath());
            DefaultMutableTreeNode parentNode = this.root;

            String[] path = myAtomic.getPath().split("[.]");
            T.o("path length: " + path.length);

            for (int i = 0; i < path.length; i++) {

                String nodePart = path[i];
                boolean isNode = false;

                if (parentNode != null) {
                    T.o("parentNode is not null");
                    T.o("parentNode: " + parentNode);
                    for (int j = 0; j < parentNode.getChildCount(); j++) {
                        DefaultMutableTreeNode name = (DefaultMutableTreeNode) parentNode.getChildAt(j);
                        T.o("parentnode name: " + name.toString());
                        if (nodePart.equalsIgnoreCase(name.toString())) {
                            T.o("node already exists");
                            isNode = true;
                            parentNode = name;
                            continue;
                        }
                    }
                } else {
                    T.o("parentNode is null");
                }

                if (isNode) {
                    continue;
                }

                // ADD BRANCH
                if (i < path.length - 1) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodePart, true);
                    T.o("d: " + nodePart);
                    parentNode = addObject(parentNode, node, true, true);
                    // ADD LEAF
                } else {
                    T.o("f: " + path[i]);
                    addObject(parentNode, myAtomic, false, true);
                }

            }

        }

    }

    public void walk(TreeModel model, Object o) {
        int childCount = model.getChildCount(o);
        T.o("childCount: " + childCount);
        for (int i = 0; i < childCount; i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) model.getChild(o, i);
            if (model.isLeaf(child) && !child.getAllowsChildren()) {
                //System.out.println(child.toString());
                MyAtomic myAtomic = (MyAtomic) child.getUserObject();
                String sql = "INSERT OR IGNORE INTO `atomic`"
                        + " (`level`, `index`, `path`, `login`, `password`, `url`, `comment`)"
                        + " VALUES"
                        + " ("
                        + " '" + child.getLevel() + "',"
                        + " '" + child.getParent().getIndex(child) + "',"
                        + " '" + getPath(child) + "',"
                        + " '" + C.encrypt(Main.password, myAtomic.getLogin().toString()) + "',"
                        + " '" + C.encrypt(Main.password, myAtomic.getPassword().toString()) + "',"
                        + " '" + C.encrypt(Main.password, myAtomic.getUrl().toString()) + "',"
                        + " '" + C.encrypt(Main.password, myAtomic.getComment().toString()) + "'"
                        + ");";
                T.o("sql: " + sql);
                AqbSqlite.insert(sql);
            } else {
                walk(model, child);
            }
        }
    }

    public String getPath(DefaultMutableTreeNode node) {
        String path = "";
        boolean isAfterRoot = false;

        T.o("node level:" + node.getLevel());
        for (TreeNode n : node.getPath()) {
            path += "." + n.toString();
        }
        path = path.substring(1);
        T.o("remove dot(.) :" + path);
        T.o("get prefix: " + path.substring(0, ("root.").length()));
        T.o("path skip: " + path.substring("root.".length()));
        path = (path.substring(0, ("root.").length()).equals("root.") ? path.substring("root.".length()) : path);
        T.o("cut prefix: " + path);
        return path;
    }
}
