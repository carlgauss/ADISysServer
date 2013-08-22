package integration.dao;

import integration.connector.Connector;
import integration.connector.ConnectorFactory;

import java.util.List;

public abstract class HQSQLDAO<Entity> implements DAO<Entity> {

    protected Connector connector = ConnectorFactory.buildIstance();

    protected static final int FIRST = 0;

    @Override
    public abstract void create(Entity entity);

    @Override
    public abstract void update(Entity entity);

    @Override
    public abstract void delete(Entity entity);

    @Override
    public abstract Entity read(String ID);

    @Override
    public abstract List<Entity> getAll();
}
