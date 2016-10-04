package ru.sbt;


import java.net.MalformedURLException;
import java.net.URL;

public class PluginManager {

    private final String pluginRootDirectory;

    static {
        try {
            PluginManager.class.getClassLoader().loadClass("ru.sbt.Plugin");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Interface Plugin not found", e);
        }
    }

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (Plugin) new PluginClassLoader(new URL[]{new URL(pluginRootDirectory + pluginName)}).loadClass(pluginClassName).newInstance();
    }

}
