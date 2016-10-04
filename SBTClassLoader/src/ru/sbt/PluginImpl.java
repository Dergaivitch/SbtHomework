package ru.sbt;

public class PluginImpl implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println(this.getClass().toString() + "doUsefull");
    }
}
