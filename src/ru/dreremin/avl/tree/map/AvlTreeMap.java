package ru.dreremin.avl.tree.map;

import java.util.Comparator;

public class AvlTreeMap<K, V> implements BinarySearchTree<K, V> {

    private Node<K, V> root;
    private final Comparator<K> comparator;

    public AvlTreeMap() {
        root = null;
        comparator = null;
    }

    public AvlTreeMap(Comparator<K> comparator) {
        root = null;
        this.comparator = comparator;
    }

    private static class Entry<K, V> {
        private final K key;
        private final V value;

        private Entry(K key, V value) {
            if (key == null) {
                throw new NullPointerException("Key cannot be null");
            }
            this.key = key;
            this.value = value;
        }
    }

    private static class Node<K, V> {
        private Entry<K, V> entry;
        private Node<K, V> leftSon;
        private Node<K, V> rightSon;

        private Node(Entry<K, V> entry) {
            this.entry = entry;
            if (entry == null) {
                throw new NullPointerException("Entry cannot be null");
            }
            this.leftSon = null;
            this.rightSon = null;
        }

        private Node(Entry<K, V> entry,
                     Node<K, V> leftSon,
                     Node<K, V> rightSon) {
            this.entry = entry;
            this.leftSon = leftSon;
            this.rightSon = rightSon;
        }
    }

    private Node<K, V> searchNodeWithComparable(Node<K, V> node, K key) {
        if (node == null) { return null; }
        Comparable<? super K> comparable = (Comparable<? super K>) key;
        if (comparable.compareTo(node.entry.key) > 0) {
            return searchNodeWithComparable(node.rightSon, key);
        }
        if (comparable.compareTo(node.entry.key) < 0) {
            return searchNodeWithComparable(node.leftSon, key);
        }
        return node;
    }

    private Node<K, V> searchNodeWithComparator(Node<K, V> node, K key) {
        if (node == null) { return null; }
        if (comparator.compare(key, node.entry.key) > 0) {
            return searchNodeWithComparator(node.rightSon, key);
        }
        if (comparator.compare(key, node.entry.key) < 0) {
            return searchNodeWithComparator(node.leftSon, key);
        }
        return node;
    }

    private void checkingForComparability(K key) {
        if (comparator == null && !(key instanceof Comparable)) {
            throw new ClassCastException("Key type does not allow comparison");
        }
    }

    @Override
    public boolean containsKey(K key) {
        checkingForComparability(key);
        if (root == null) { return false; }
        Node<K, V> node = (comparator != null)
                ? searchNodeWithComparator(root, key)
                : searchNodeWithComparable(root, key);
        return node != null;
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public boolean insert(K key, V value) { return true; }

    @Override
    public V remove(K key) { return null; }

    @Override
    public V get(K key) { return null; }
}
