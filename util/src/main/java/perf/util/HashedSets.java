package perf.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Created by wreicher
 */
public class HashedSets<K,V> implements Serializable{

    private HashMap<K,HashSet<V>> sets;
    private Set<V> empty;
    private boolean linked;
    public HashedSets(){
        this(true);
    }
    public HashedSets(boolean linked){
        sets = linked ? new LinkedHashMap<>() : new HashMap<>();
        empty = Collections.unmodifiableSet( new HashSet<>() );
        this.linked = linked;
    }
    public void put(K name, V value){
        if(!sets.containsKey(name)){
            sets.put(name,linked ? new LinkedHashSet<>() : new HashSet<>());
        }
        sets.get(name).add(value);
    }
    public void putAll(K name, Collection<V> values){
        if(!sets.containsKey(name)){
            sets.put(name, linked ? new LinkedHashSet<V>() : new HashSet<V>());
        }
        sets.get(name).addAll(values);
    }
    public Set<V> get(K key){
        if(sets.containsKey(key)){
            return Collections.unmodifiableSet(sets.get(key));
        } else {
            return empty;
        }
    }
    public boolean has(K key){
        return sets.containsKey(key);
    }
    public Set<K> keys(){
        return Collections.unmodifiableSet(sets.keySet());
    }
    public int size(){return sets.size();}
    public boolean isEmpty(){return sets.isEmpty();}

    public void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(empty);
        stream.writeObject(sets);
    }
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        empty = (Set<V>)stream.readObject();
        sets = (HashMap<K,HashSet<V>>) stream.readObject();
    }
    private void readObjectNoData()
            throws ObjectStreamException {

    }

    public void forEach(BiConsumer<K,Set<V>> consumer){
        sets.forEach(consumer);
    }

    public Stream<Map.Entry<K, HashSet<V>>> stream(){return sets.entrySet().stream();}

    public Iterator<Map.Entry<K,HashSet<V>>> iterator(){return sets.entrySet().iterator();}
}
