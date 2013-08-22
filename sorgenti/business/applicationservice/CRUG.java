package business.applicationservice;

import java.util.List;

import business.applicationservice.exception.InvalidInfermiereFieldException;
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
    public void create(Parameter parameter) throws InvalidInfermiereFieldException;
    
    public void update(Parameter parameter) throws InvalidInfermiereFieldException;
    
    public Entity read(Parameter parameter);
    
    public List<Entity> getAll(Parameter parameter);
}
