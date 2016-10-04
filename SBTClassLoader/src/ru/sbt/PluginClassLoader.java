package ru.sbt;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {

    public PluginClassLoader(URL[] url) {
        super(url);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("javaPlugin")) {
            return super.loadClass(name);
        }
        return findClass(name);
    }

}