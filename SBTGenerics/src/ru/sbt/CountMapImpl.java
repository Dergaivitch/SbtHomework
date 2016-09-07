package ru.sbt;
import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {

    private final Map<E, Integer> map = new HashMap<>();

    @Override
    public void add(E e) {
        map.put(e, getCount(e) + 1);
    }

    @Override
    public int getCount(E e) {
        return map.get(e) == null ? 0 : map.get(e);
    }

    @Override
    public int remove(E e) {
        return map.remove(e) == null ? 0 : map.remove(e);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends E> source) {
        CountMapImpl<E> cmi = (CountMapImpl<E>) source;
        for (Map.Entry<E, Integer> entry : cmi.map.entrySet()) {
            int count = this.getCount(entry.getKey());
            map.put(entry.getKey(), entry.getValue() + count);
        }
    }

    @Override
    public Map<E, Integer> toMap() {
        Map<E, Integer> newMap = new HashMap<>();
        newMap.putAll(map);
        return newMap;
    }

    @Override
    public void toMap(Map<? super E, Integer> destination) {
        destination.putAll(map);
    }


}