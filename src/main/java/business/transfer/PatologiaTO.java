package business.transfer;

import business.entity.Patologia;

public class PatologiaTO extends Patologia {

    public  PatologiaTO(Patologia patologia) {
        setCodice(patologia.getCodice());
        setNome(patologia.getNome());
        setGravita(patologia.getGravita());
    }

    private boolean toInsert;

    public boolean isToInsert() {
        return toInsert;
    }

    public void setToInsert(boolean toInsert) {
        this.toInsert = toInsert;
    }
}
