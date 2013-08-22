package business.applicationservice;

import business.applicationservice.exception.InvalidInfermiereFieldException;
import utility.Parameter;

import java.util.List;

/**
 *  CRUG is an acronym:
 *   Create
 *   Read
 *   Update
 *   GetAll
 *
 * We don't implement delete operations.
 */
public interface CRUG<Entity> {
    public void create(Parameter parameter) throws InvalidInfermiereFieldException;

    public void update(Parameter parameter) throws InvalidInfermiereFieldException;

    public Entity read(Parameter parameter);

    public List<Entity> getAll(Parameter parameter);
}
