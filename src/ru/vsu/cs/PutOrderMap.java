package ru.vsu.cs;

import ru.vsu.cs.util.dummy.DefaultNotSupportedCollection;
import ru.vsu.cs.util.dummy.DefaultNotSupportedMap;
import ru.vsu.cs.util.dummy.DefaultNotSupportedSet;

import java.util.*;

public class PutOrderMap <K extends Comparable<? super K>, V> implements DefaultNotSupportedMap<K, V> {

    private class Pair<K, V> implements Entry<K, V> {
        public K key;
        public V value;
        public int order;

        public Pair(K key, V value, int order) {
            this.key = key;
            this.value = value;
            this.order = order;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    Map<K, Pair<K, V>> keyMap = new TreeMap<>();
    Map<Integer, Pair<K, V>> orderMap = new TreeMap<>();
    int order = 0;

    @Override
    public V get(Object key) {
        Pair<K, V> pair = keyMap.get(key);
        return (pair == null) ? null : pair.value;
    }

    @Override
    public V put(K key, V value) {
        Pair<K, V> pair = keyMap.get(key);
        if (pair != null) {
            V oldValue = pair.value;
            pair.value = value;
            return oldValue;
        } else {
            pair = new Pair<>(key, value, order);
            keyMap.put(key, pair);
            orderMap.put(order, pair);
            order++;
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        Pair<K, V> pair = keyMap.remove(key);
        int order = pair.order;
        orderMap.remove(order);
        return pair.value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.entrySet().forEach((entry) -> {
            keyMap.put(entry.getKey(), new Pair(entry.getKey(), entry.getValue(), order));
            orderMap.put(order, new Pair<>(entry.getKey(), entry.getValue(), order));
            order++;
        });
    }

    @Override
    public void clear() {
        keyMap.clear();
        orderMap.clear();
        order = 0;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new DefaultNotSupportedSet<Entry<K, V>>() {
            @Override
            public int size() {
                return PutOrderMap.this.size();
            }

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return (Iterator<Map.Entry<K, V>>) (Object) orderMap.values().iterator();
            }
        };
    }

    @Override
    public Set<K> keySet() {
        return new DefaultNotSupportedSet<K>() {
            Iterator<Map.Entry<K, V>> entryIterator = entrySet().iterator();

            @Override
            public int size() {
                return PutOrderMap.this.size();
            }

            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    @Override
                    public boolean hasNext() {
                        return entryIterator.hasNext();
                    }

                    @Override
                    public K next() {
                        return entryIterator.next().getKey();
                    }

                };
            }
        };
    }

    @Override
    public Collection<V> values() {
        return new DefaultNotSupportedCollection<V>() {
            Iterator<Map.Entry<K, V>> entryIterator = entrySet().iterator();

            @Override
            public int size() {
                return PutOrderMap.this.size();
            }

            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    @Override
                    public boolean hasNext() {
                        return entryIterator.hasNext();
                    }

                    @Override
                    public V next() {
                        return entryIterator.next().getValue();
                    }
                };
            }
        };
    }
}
