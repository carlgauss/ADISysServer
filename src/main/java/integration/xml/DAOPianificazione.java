package integration.xml;

import business.entity.Intervento;
import org.xml.sax.SAXException;

import java.util.List;

public interface DAOPianificazione {

    public abstract void export(List<Intervento> listaInterventi)
            throws SAXException;

}