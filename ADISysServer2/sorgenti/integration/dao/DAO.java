package integration.dao;

import util.Parameter;

public interface DAO<Entity> {
    public Object create(Entity entity);
    
    public Object update(Entity entity);
    
    public Object delete(Entity entity);
    
    public Object read(String ID);
    
    public Object getAll();
}
