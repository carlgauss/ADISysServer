package business.entity;

import java.util.List;

import javax.xml.bind.annotation.*;

import util.SerialClone;

@XmlRootElement
public class Pianificazione implements Entity {
	
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 919250330191402749L;
	

	private List<Intervento> intervento;

	@XmlElements({ 
	    @XmlElement(name="intervento", type=Intervento.class)
	})
	public List<Intervento> getIntervento() {
		return SerialClone.clone(intervento);
	}

	public void setIntervento(List<Intervento> intervento) {
		this.intervento = SerialClone.clone(intervento);
	}
}
