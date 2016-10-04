package ru.sbt;

import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {

        Plugin p = new PluginImpl();
        p.doUsefull();

        PluginManager pm = new PluginManager("/Users/user/IdeaProjects/sbt_homework/src/ru/sbt/");

        Plugin p2 = pm.load("pluginDir/plugin2/", "ru.sbt.PluginImpl");
        p2.doUsefull();

        Plugin p3 = pm.load("pluginDir/plugin1/", "ru.sbt.PluginImpl");
        p3.doUsefull();

    }
}
