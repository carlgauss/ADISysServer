package presentation.boundary.controller;

import business.transfer.Parameter;


public abstract class SchermataImmissione extends Schermata {
    protected boolean isEdit;

    @Override
    public void initData(Parameter parameter) {
        super.initData(parameter);

        if (parameter == null) {
            initializeAdd();
            isEdit = false;
        } else {
            initializeEdit();
            isEdit = true;
        }
    }

    protected abstract void initializeAdd();

    protected abstract void initializeEdit();
}
