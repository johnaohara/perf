package perf.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wreicher
 */
public class Counters<T> implements Serializable{

    private ConcurrentHashMap<T,AtomicInteger> counts;
    private AtomicInteger sum;
    public Counters() {
        counts = new ConcurrentHashMap<>();sum = new AtomicInteger(0);
    }
    public void add(T t) {
        if(!contains(t)) {
            counts.put(t,new AtomicInteger(0));
        }
        counts.get(t).incrementAndGet();
        sum.incrementAndGet();
    }

    public boolean contains(T t) {
        return counts.containsKey(t);
    }

    public int count(T t) {
        if(contains(t)) {
            return counts.get(t).get();
        } else {
            return 0;
        }
    }
    public int sum(){return sum.get();}
    public int size(){return counts.size();}
    public List<T> entries(){
        return Arrays.asList((T[]) counts.keySet().toArray());

    }
}
