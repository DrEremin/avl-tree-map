package ru.dreremin.avl.tree.map;

import java.util.Comparator;
import java.util.function.BiConsumer;

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

        /*public K getKey() { return key; }*/
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

        /*public Node<K, V> getLeftSon() { return leftSon; }
        public Entry<K, V> getEntry() { return entry; }*/

        @Override
        public String toString() {
            return new StringBuilder("{")
                    .append(entry.key)
                    .append(" - ")
                    .append(entry.value)
                    .append("}")
                    .toString();
        }
    }

    private Node<K, V> searchNodeWithComparable(Node<K, V> node, K key) {

        Comparable<K> comparable = (Comparable<K>) key;

        if (comparable.compareTo(node.entry.key) > 0 && node.rightSon != null) {
            return searchNodeWithComparable(node.rightSon, key);
        }
        if (comparable.compareTo(node.entry.key) < 0 && node.leftSon != null) {
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

    private void insertWithComparable(K key, V value) {

        Comparable<K> comparable = (Comparable<K>) key;
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

    private <T> void inorderTraversal(Node<K, V> node,
                                      BiConsumer<Node<K, V>, T> combiner,
                                      T accumulator) {
        if (node == null) { return; }
        inorderTraversal(node.leftSon, combiner, accumulator);
        combiner.accept(node, accumulator);
        inorderTraversal(node.rightSon, combiner, accumulator);
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
        if (comparator != null) {
            return comparator.compare(key,
                    searchNodeWithComparator(root, key).entry.key) == 0;
        } else {
            return ((Comparable<K>) key).compareTo(
                    searchNodeWithComparable(root, key).entry.key) == 0;
        }
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void put(K key, V value) {
        checkingForComparability(key);
        if (root == null) {
            root = new Node<>(new Entry<>(key, value));
            return;
        }
        if (comparator != null) { insertWithComparator(key, value); }
        else { insertWithComparable(key, value); }
    }

    @Override
    public V remove(K key) {
        checkingForComparability(key);
        if (root == null) { return null; }


        return null;
    }

    @Override
    public V get(K key) {
        checkingForComparability(key);
        if (root == null) { return null; }

        Node<K, V> node;

        if (comparator != null) {
            node = searchNodeWithComparator(root, key);
            if (comparator.compare(key, node.entry.key) == 0) {
                return node.entry.value;
            }
        } else {
            node = searchNodeWithComparable(root, key);
            if (((Comparable<K>) key).compareTo(node.entry.key) == 0) {
                return node.entry.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("[");

        inorderTraversal(
                root,
                (Node<K, V> n, StringBuilder b) -> b
                        .append(n.toString())
                        .append(", "),
                builder);
        return builder
                .replace(builder.length() - 2, builder.length(), "]")
                .toString();
    }
}
