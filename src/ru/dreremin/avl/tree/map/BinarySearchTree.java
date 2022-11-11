package ru.dreremin.avl.tree.map;

public interface BinarySearchTree<K, V> {

    boolean containsKey(K key);
    boolean isEmpty();
    boolean put(K key, V value);
    V remove(K key);
    V get(K key);
}
