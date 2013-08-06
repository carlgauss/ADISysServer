package integration.dao;

import java.util.List;

import business.entity.Paziente;

public class DAOPaziente extends HQSQLDAO<Paziente> {

	@Override
	public void create(Paziente entity) {

		
	}

	@Override
	public void update(Paziente entity) {

	}

	@Override
	public void delete(Paziente entity) {
		//not implemented yet
	}

	@Override
	public Paziente read(String ID) {

		return null;
	}

	@Override
	public List<Paziente> getAll() {

		return null;
	}

}
