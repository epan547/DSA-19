import java.lang.reflect.Array;
import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // calculate avg number of entries per bucket by doing size / buckets.length

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private Map<K,V> Map;
    private int size = 0;


    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
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
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        // TODO
        // hint: use key.hashCode() to calculate the key's hashCode using its built in hash function
        // then use % to choose which bucket to return.
        int hash = Math.abs(key.hashCode()) % buckets.length;
        return buckets[hash];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        // TODO
        if(keySet().contains(key)){
            return true;
        }
        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        // TODO
        if(values().contains(value)){
            return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        // TODO
        if(this.containsKey(key)){
            LinkedList<Entry> L = this.chooseBucket(key);

            for (int i = 0; i < L.size(); i++) {
                    if (L.get(i).key.equals(key)) {
                        return L.get(i).value;
                    }
                }
            }
        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, accord = value;ing to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        // TODO: Complete this method
        // hint: use chooseBucket() to determine which bucket to place the pair in
        // hint: use rehash() to appropriately grow the hashmap if needed

        // Make it bigger if too densely populated
        if(((double)size) > ALPHA*buckets.length) {
            this.rehash(GROWTH_FACTOR);
        }

        if(this.containsKey(key)) {
            LinkedList<Entry> L = this.chooseBucket(key);
            if(L.size() != 0) {
                for (int i = 0; i < L.size(); i++) {
                    if (L.get(i).key.equals(key)) {
                        Entry en = L.get(i);
                        V val = en.value;
                        en.setValue(value);
                        return val;
                    }
                }
            }
            Entry e = new Entry(key,value);
            L.add(e);

        } else {
            if(this.chooseBucket(key) != null) {
                LinkedList<Entry> list = this.chooseBucket(key);
                Entry en = new Entry(key,value);
                list.add(en);
                size ++;
            }

        }
        return null;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        // TODO
        // hint: use chooseBucket() to determine which bucket the key would be
        // hint: use rehash() to appropriately grow the hashmap if needed
        if(((double)size) < BETA*buckets.length && buckets.length >= 32) {
            this.rehash(SHRINK_FACTOR);
        }

        if(this.containsKey(key)){
            LinkedList<Entry> L = this.chooseBucket(key);
            for (int i = 0; i < L.size(); i++) {
                if (L.get(i).key.equals(key)) {
                    Entry E = L.get(i);
                    L.remove(i);
                    size --;
                    return E.value;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        // TODO
        // hint: once you have removed all values from the buckets, use put(k, v) to add them back in the correct place

        // saving current buckets
        LinkedList<Entry>[] old_buckets = buckets;
        // creating new, bigger buckets
        int new_len = (int) Math.round(buckets.length*growthFactor);
        initBuckets(new_len);
        size = 0;

        for (LinkedList<Entry> map : old_buckets) {
            while(map.size()!=0){
                Entry en = map.pop();
                this.put(en.getKey(), en.getValue());
            }
        }
        int i = 0;

    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
