package integration.xml;

import java.util.List;

import org.xml.sax.SAXException;

import business.entity.Intervento;

public interface DAOPianificazione {

	public abstract void export(List<Intervento> listaInterventi)
			throws SAXException;

}