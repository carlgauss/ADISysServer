package business.applicationservice.checker;

import business.entity.Entity;

public class CheckerFactory {
    private static final String CHECKER_STRING = Checker.class.getSimpleName();
    private static final String CHECKER_PKG_PATH = "business.applicationservice.checker";

    private  CheckerFactory() {

    }

    public static Checker buildInstance(Class<? extends Entity> entityClass) {
        String entityClassName = entityClass.getSimpleName();
        String resultClassName = CHECKER_PKG_PATH;
        resultClassName += entityClassName;
        resultClassName += CHECKER_STRING;
        Checker result = null;
        try {
            Class<?> resulClass = Class.forName(resultClassName);
            result = (Checker) resulClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
