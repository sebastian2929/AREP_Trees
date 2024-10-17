package edu.eci.arep.demo;

import java.util.*;

public class BST<E extends Comparable<E>> implements List<E> {
    private class Node {
        E value;
        Node left, right;

        Node(E value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    // Insertion
    public void insert(E value) {
        root = insertRec(root, value);
        size++;
    }

    private Node insertRec(Node root, E value) {
        if (root == null) {
            return new Node(value);
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    // Search
    public Node search(E value) {
        return searchRec(root, value);
    }

    private Node searchRec(Node root, E value) {
        if (root == null || root.value.equals(value)) {
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            return searchRec(root.left, value);
        }
        return searchRec(root.right, value);
    }

    // Deletion
    public void delete(E value) {
        root = deleteRec(root, value);
        size--;
    }

    private Node deleteRec(Node root, E value) {
        if (root == null)
            return null;

        if (value.compareTo(root.value) < 0) {
            root.left = deleteRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.value = findMinRec(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    // In-order Traversal
    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(Node root, List<E> result) {
        if (root != null) {
            inOrderRec(root.left, result);
            result.add(root.value);
            inOrderRec(root.right, result);
        }
    }

    // Find Minimum
    public E findMin() {
        if (root == null)
            throw new NoSuchElementException("The tree is empty.");
        return findMinRec(root);
    }

    private E findMinRec(Node root) {
        return root.left == null ? root.value : findMinRec(root.left);
    }

    // Find Maximum
    public E findMax() {
        if (root == null)
            throw new NoSuchElementException("The tree is empty.");
        return findMaxRec(root);
    }

    private E findMaxRec(Node root) {
        return root.right == null ? root.value : findMaxRec(root.right);
    }

    // Height
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node root) {
        if (root == null)
            return -1;
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    // Count Nodes
    public int countNodes() {
        return size;
    }

    // Level-Order Traversal
    public List<E> levelOrderTraversal() {
        List<E> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.value);
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }

        return result;
    }

    // List<E> methods
    @Override
    public boolean add(E e) {
        insert(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Comparable) {
            delete((E) o);
            return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        List<E> inOrder = inOrderTraversal();
        return inOrder.get(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return search((E) o) != null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(Object o) {
        List<E> inOrder = inOrderTraversal();
        return inOrder.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        List<E> inOrder = inOrderTraversal();
        return inOrder.lastIndexOf(o);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E element : c) {
            if (add(element))
                modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return addAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element))
                modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<E> toRemove = new ArrayList<>();
        for (E value : inOrderTraversal()) {
            if (!c.contains(value)) {
                toRemove.add(value);
            }
        }
        for (E value : toRemove) {
            remove(value);
        }
        return !toRemove.isEmpty();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> inOrder = inOrderTraversal();
        return inOrder.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return inOrderTraversal().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return inOrderTraversal().toArray(a);
    }

    @Override
    public Iterator<E> iterator() {
        return inOrderTraversal().iterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Remove by index is not supported in a BST.");
    }
}
