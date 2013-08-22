package integration.dao;

import java.util.List;

public interface DAO<Entity> {
    public void create(Entity entity);

    public void update(Entity entity);

    public void delete(Entity entity);

    public Entity read(String ID);

    public List<Entity> getAll();
}
