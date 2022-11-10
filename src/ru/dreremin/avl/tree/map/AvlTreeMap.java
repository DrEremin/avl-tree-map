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
