package ru.dreremin.avl.tree.map;

import java.util.Comparator;

public class AvlTreeMap<K, V> implements BinarySearchTree<K, V> {

    private Node<K, V> root;
    private Comparator<K> comparator;

    public AvlTreeMap() {
        root = null;
        comparator = null;
    }

    public AvlTreeMap(Comparator<K> comparator) {
        root = null;
        this.comparator = comparator;
    }

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

        private Node(Entry<K, V> entry) {
            this.entry = entry;
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

        private Entry<K, V> getEntry() { return entry; }
        private Node<K, V> getLeftSon() { return leftSon; }
        private Node<K, V> getRightSon() { return rightSon; }
        private void setLeftSon(Node<K, V> node) { leftSon = node; }
        private void setRightSon(Node<K, V> node) { rightSon = node; }
    }

    private <U extends Comparable<? super K>> Node<K, V>
            searchNodeWithComparable(Node<K, V> node, U key) {
        if (node == null) { return null; }
        if (key.compareTo(node.entry.key) > 0) {
            return searchNodeWithComparable(node.rightSon, key);
        }
        if (key.compareTo(node.entry.key) < 0) {
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

    @Override
    public boolean containsKey(K key) {
        return true;
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
