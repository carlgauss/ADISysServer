package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;

import java.util.List;

public interface Checker {
    public void check(List<Object> values) throws CommonException;
}
