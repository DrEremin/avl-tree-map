package ru.dreremin.avl.tree.map;

public class AvlTreeMap<K, V> implements BinarySearchTree<K, V> {

    private static class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() { return key; }
        private V getValue() { return value; }
    }

    private static class Node<K, V> {
        Entry<K, V> entry;
        Node<K, V> leftSon;
        Node<K, V> rightSon;
    }

    @Override
    public boolean containsKey(K key) { return true; }

    @Override
    public boolean isEmpty() { return true; }

    @Override
    public boolean insert(K key, V value) { return true; }

    @Override
    public V remove(K key) { return null; }

    @Override
    public V get(K key) { return null; }
}
