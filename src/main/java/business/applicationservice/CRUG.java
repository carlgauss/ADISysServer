package business.applicationservice;

import business.applicationservice.exception.CommonException;
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
    public void create(Parameter parameter) throws CommonException;

    public void update(Parameter parameter) throws CommonException;

    public Entity read(Parameter parameter);

    public List<Entity> getAll(Parameter parameter);
}
