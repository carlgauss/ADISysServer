package integration.dao;

import java.util.List;

import integration.connector.Connector;
import integration.connector.ConnectorFactory;

public abstract class HQSQLDAO<Entity> implements DAO<Entity> {
	
	protected Connector connector = ConnectorFactory.buildIstance();

	@Override
	public abstract void create(Entity entity);

	@Override
	public abstract void update(Entity entity);

	@Override
	public abstract void delete(Entity entity);

	@Override
	public abstract Entity read(String ID);

	@Override
	public abstract List<Entity> getAll() ;
}
