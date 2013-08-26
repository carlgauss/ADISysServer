package presentation.boundary.controller.component;

import javafx.scene.control.TreeItem;

public class TreeChild<T> extends TreeItem<T> {
    protected boolean isRootChild;

    public TreeChild(T item) {
        super(item);
    }

    public TreeChild() {
    }


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

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeChild item = new TreeChild(cellLabel);
        getChildren().add(item);
    }
}
