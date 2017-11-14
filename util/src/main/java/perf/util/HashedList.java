package perf.util;

import java.util.*;

/**
 * Created by wreicher
 * A list that only supports items being added once (really? this doesn't exit? I'm probably just being too lazy to search).
 * Also has getFirst and getLast like a Dequeue
 */
public class HashedList<V> {

    private HashSet<V> seen;
    private LinkedList<V> data;

    public HashedList(){
        this(Collections.emptyList());
    }
    public HashedList(List<V> values){
        this.seen = new HashSet<V>();
        this.data = new LinkedList<V>();
        addAll(values);
    }
    public boolean addAll(Collection<V> values){
        boolean rtrn = true;
        if(!values.isEmpty()){
            for(V v : values){
                boolean addRtrn = add(v);//moved to separate line to avoid ordering bug
                rtrn = addRtrn && rtrn;
            }
        }
        return rtrn;
    }
    public boolean add(V value){
        if(seen.contains(value)){
            return false;
        }
        synchronized (this){
            if(seen.contains(value)){
                return false;
            }
            seen.add(value);
            data.add(value);
            return true;
        }
    }
    public boolean removeAll(Collection<V> values){
        boolean rtrn = true;
        if(!values.isEmpty()){
            for(V v : values){
                rtrn = rtrn && remove(v);
            }
        }
        return rtrn;
    }

    public boolean remove(V value){
        if(seen.contains(value)){
            synchronized (this) {
                seen.remove(value);
                data.remove(value);
            }
            return true;
        }
        return false;
    }
    public boolean contains(V value){
        return seen.contains(value);
    }
    public int size(){
        return data.size();
    }
    public V getLast(){
        return data.getLast();
    }
    public V getFirst(){
        return data.getFirst();
    }
    public Iterator<V> iterator(){
        return data.iterator();
    }
    public List<V> toList(){return Collections.unmodifiableList(data);}
}
