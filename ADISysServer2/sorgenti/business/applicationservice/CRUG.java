package business.applicationservice;

import util.Parameter;

/*
 * CRUG is an acronym for:
 *   Create
 *   Read
 *   Update
 *   GetAll
 * 
 */
public interface CRUG {
    public void create(Parameter parameter);
    
    public void update(Parameter parameter);
    
    public Object read(Parameter parameter);
    
    public Object[] getAll(Parameter parameter);
}
