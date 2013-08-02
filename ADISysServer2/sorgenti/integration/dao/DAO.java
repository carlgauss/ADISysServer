package integration.dao;

import util.Parameter;

public interface DAO {
    public Object create(Parameter parameter);
    
    public Object update(Parameter parameter);
    
    public Object delete(Parameter parameter);
    
    public Object read(Parameter parameter);
    
    public Object getAll(Parameter parameter);
}
