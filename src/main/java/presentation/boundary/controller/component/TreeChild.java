package presentation.boundary.controller.component;

import javafx.scene.control.TreeItem;

public abstract class TreeChild<T> extends TreeItem<T> {
    protected boolean isRootChild;


    public boolean isRootChild() {
        return isRootChild;
    }

    public void setRootChild(boolean value) {
        isRootChild = value;
    }

    public TreeChild getRootChild() {
        TreeChild rootChild = null;

        if (isRootChild) {
            rootChild = this;
        } else {
            TreeChild parent = (TreeChild) getParent();
            if (parent != null) {
                rootChild = parent.getRootChild();
            }
        }

        return rootChild;
    }
}
