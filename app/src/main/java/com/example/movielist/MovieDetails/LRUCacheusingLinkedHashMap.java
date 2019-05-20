package com.example.movielist.MovieDetails;

import java.util.LinkedHashMap;

public class LRUCacheusingLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

  private static LRUCacheusingLinkedHashMap instance;

    private final int capacity;

   public static LRUCacheusingLinkedHashMap getInstance(int capacity) {

        if (instance == null) {

            instance = new LRUCacheusingLinkedHashMap(capacity);
        }

        return instance;

    }

    // Remove the eldest element whenever size of cache exceeds the capacity
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        if (size() > this.capacity) {
            return true;
        } else {
            return false;
        }
    }

    // Call constructor of LinkedHashMap with accessOrder set to true to
    // achieve LRU Cache behavior
    public LRUCacheusingLinkedHashMap(int capacity) {
        super(capacity + 1, 1.0f, true);
        this.capacity = capacity;
    }

// Returns the value corresponding to input key.
    public V find(K key) {
        return super.get(key);
    }

//Setting the element with input key and value in the cache. If the element
    //already exits, it updates its value.
    public void add(K key, V value) {
        super.put(key, value);
    }

    // to check if the inputKey Exists
    public boolean isValid(K key){
        if(super.containsKey(key)){
            return true;
        }
        else{
            return false;
        }
    }
}
