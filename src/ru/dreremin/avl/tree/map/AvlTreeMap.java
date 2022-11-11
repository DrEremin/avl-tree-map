package ru.dreremin.avl.tree.map;

import java.util.Comparator;

public class AvlTreeMap<K, V> implements BinarySearchTree<K, V> {

    private Node<K, V> root;
    private final Comparator<? super K> comparator;

    public AvlTreeMap() {
        root = null;
        comparator = null;
    }

    public AvlTreeMap(Comparator<? super K> comparator) {
        root = null;
        this.comparator = comparator;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

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

        Comparable<? super K> comparable = (Comparable<? super K>) key;

        if (comparable.compareTo(node.entry.key) > 0 && node.rightSon != null) {
            return searchNodeWithComparable(node.rightSon, key);
        }
        if (comparable.compareTo(node.entry.key) < 0 && node.rightSon != null) {
            return searchNodeWithComparable(node.leftSon, key);
        }
        return node;
    }

    private Node<K, V> searchNodeWithComparator(Node<K, V> node, K key) {
        if (comparator.compare(key, node.entry.key) > 0
                && node.rightSon != null) {
            return searchNodeWithComparator(node.rightSon, key);
        } else if (comparator.compare(key, node.entry.key) < 0
                && node.leftSon != null) {
            return searchNodeWithComparator(node.leftSon, key);
        }
        return node;
    }

    private void checkingForComparability(K key) {
        if (comparator == null && !(key instanceof Comparable)) {
            throw new ClassCastException("Key type does not allow comparison");
        }
    }

    private void insertWithComparable(K key, V value) {

        Comparable<? super K> comparable = (Comparable<? super K>) key;
        Node<K, V> targetNode = searchNodeWithComparable(root, key);

        if (comparable.compareTo(targetNode.entry.key) == 0) {
            targetNode.entry = new Entry<>(key, value);
        } else if (comparable.compareTo(targetNode.entry.key) > 0) {
            targetNode.rightSon = new Node<>(new Entry<>(key, value));
        } else {
            targetNode.leftSon = new Node<>(new Entry<>(key, value));
        }
    }

    private void insertWithComparator(K key, V value) {

        Node<K, V> targetNode = searchNodeWithComparator(root, key);

        if (comparator.compare(key, targetNode.entry.key) == 0) {
            targetNode.entry = new Entry<>(key, value);
        } else if (comparator.compare(key, targetNode.entry.key) > 0) {
            targetNode.rightSon = new Node<>(new Entry<>(key, value));
        } else {
            targetNode.leftSon = new Node<>(new Entry<>(key, value));
        }
    }

    @Override
    public boolean containsKey(K key) {
        checkingForComparability(key);
        if (root == null) { return false; }
        if (comparator != null) {
            return comparator.compare(key,
                    searchNodeWithComparator(root, key).entry.key) == 0;
        } else {
            return ((Comparable<? super K>) key).compareTo(
                    searchNodeWithComparable(root, key).entry.key) == 0;
        }
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public boolean put(K key, V value) {
        checkingForComparability(key);
        if (root == null) { return false; }
        if (comparator != null) { insertWithComparator(key, value); }
        else { insertWithComparable(key, value); }
        return true;
    }

    @Override
    public V remove(K key) { return null; }

    @Override
    public V get(K key) { return null; }
}
