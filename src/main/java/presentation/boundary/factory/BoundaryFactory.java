package presentation.boundary.factory;

import presentation.boundary.Boundary;
import business.transfer.Parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BoundaryFactory {
    private static final String BOUNDARY_PACKAGE_PATH = "presentation.boundary.";
    private static final String SERVICE_NAME_HEAD = "Mostra";
    private static final int BOUNDARY_NAME_START_POSITION = SERVICE_NAME_HEAD.length();

    private BoundaryFactory() {

    }

    public static Boundary getBoundary(String serviceName, Parameter parameter) {
        Class<?> boundaryClass = getBoundaryClass(serviceName);
        return newBoundaryInstance(boundaryClass, parameter);
    }

    private static String getSimpleClassName(String serviceName) {
        return serviceName.substring(BOUNDARY_NAME_START_POSITION);
    }

    private static String getCanonicalClassName(String simpleClassName) {
        return BOUNDARY_PACKAGE_PATH + simpleClassName;
    }

    private static Class<?> getBoundaryClass(String serviceName) {
        String simpleClassName = getSimpleClassName(serviceName);
        String canonicalClassName = getCanonicalClassName(simpleClassName);

        Class<?> boundaryClass = null;

        try {
            boundaryClass = Class.forName(canonicalClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return boundaryClass;
    }

    private static Boundary newBoundaryInstance(Class<?> boundaryClass, Parameter parameter) {
        Boundary boundaryInstance = null;

        try {
            Constructor constructor = boundaryClass.getConstructor(Parameter.class);
            boundaryInstance = (Boundary) constructor.newInstance(parameter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return boundaryInstance;
    }
}
