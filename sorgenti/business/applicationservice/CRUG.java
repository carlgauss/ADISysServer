package business.applicationservice;

import java.util.List;

import util.Parameter;

/*
 * CRUG is an acronym for:
 *   Create
 *   Read
 *   Update
 *   GetAll
 * 
 */
public interface CRUG<Entity> {
    public void create(Parameter parameter);
    
    public void update(Parameter parameter);
    
    public Entity read(Parameter parameter);
    
    public List<Entity> getAll(Parameter parameter);
}
