package ru.sbt;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Reflection {

    public static void assign(Object to, Object from) {

        Map<String, Method> getters = new HashMap<>();
        Class<?> fClass = from.getClass();
        Method[] methodsFrom = fClass.getMethods();
        for (Method method : methodsFrom) {
            if (method.getName().startsWith("get") && method.getReturnType() != void.class && method.getParameterCount() == 0) {
                getters.put(method.getName(), method);
            }
        }
        if (getters.isEmpty()) {
            return;
        }

        Map<String, Method> setters = new HashMap<>();
        Class<?> tCLass = to.getClass();
        Method[] methodsTo = tCLass.getMethods();
        for (Method method : methodsTo) {
            if (method.getName().startsWith("set") && method.getReturnType() == void.class && method.getParameterCount() == 1) {
                setters.put(method.getName(), method);
            }
        }
        if (setters.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Method> entry : getters.entrySet()) {
            String setMethodName = entry.getKey().replaceFirst("get", "set");
            if (setters.containsKey(setMethodName)) {
                Method set = setters.get(setMethodName);
                Class<?> setterParameterType = set.getParameterTypes()[0];

                Class<?> getterReturnType = entry.getValue().getReturnType();

                if (setterParameterType.isAssignableFrom(getterReturnType)) {
                    try {
                        set.invoke(to, entry.getValue().invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Exception");
                    }
                }
            }
        }
    }
}