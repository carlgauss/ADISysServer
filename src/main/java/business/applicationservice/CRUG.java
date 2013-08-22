package business.applicationservice;

import business.applicationservice.exception.InvalidInfermiereFieldException;
import business.applicationservice.exception.InvalidPazienteFieldException;
import utility.Parameter;

import java.util.List;

/**
 * CRUG is an acronym:
 * Create
 * Read
 * Update
 * GetAll
 * <p/>
 * We don't implement delete operations.
 */
public interface CRUG<Entity> {
    public void create(Parameter parameter) throws InvalidInfermiereFieldException, InvalidPazienteFieldException;

    public void update(Parameter parameter) throws InvalidInfermiereFieldException, InvalidPazienteFieldException;

    public Entity read(Parameter parameter);

    public List<Entity> getAll(Parameter parameter);
}
