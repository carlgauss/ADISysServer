package business.transfer;

import business.entity.Patologia;

public class PatologiaTO extends Patologia {
    private static final String BLANK = "";

    public PatologiaTO() {
        setNome(BLANK);
    }

    public PatologiaTO(Patologia patologia) {
        setCodice(patologia.getCodice());
        setNome(patologia.getNome());
        setGravita(patologia.getGravita());
    }

    private boolean toInsert;
    private int index = -1;

    public boolean isToInsert() {
        return toInsert;
    }

    public void setToInsert(boolean toInsert) {
        this.toInsert = toInsert;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
