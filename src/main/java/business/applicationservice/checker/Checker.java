package business.applicationservice.checker;

import business.applicationservice.exception.CommonInvalidFieldException;

import java.util.List;

public interface Checker {
    public void check(List<Object> values) throws CommonInvalidFieldException;
}
