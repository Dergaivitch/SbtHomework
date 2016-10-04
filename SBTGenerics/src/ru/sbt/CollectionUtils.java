package ru.sbt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T t) {
        return source.indexOf(t);
    }

    public static <T> List<T> limit(List<T> source, int size) {
        if (size < source.size()) {
            return source.subList(0, size);
        } else {
            return source;
        }
    }

    public static <T> void add(List<? super T> source, T t) {
        source.add(t);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T t : c2) {
            if (c1.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable> List<T> range(List<? extends T> list, T min, T max) {
        return range(list, min, max, Comparable::compareTo);
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> tList = newArrayList();
        addAll(list, tList);
        tList.sort(comparator);
        return tList.subList(indexOf(list, min), indexOf(list, max) + 1);
    }
}