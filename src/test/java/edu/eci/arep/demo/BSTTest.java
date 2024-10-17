package edu.eci.arep.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class BSTTest {
    private BST<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BST<>();
    }

    @Test
    public void testInsertAndSize() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(3, bst.size());
    }

    @Test
    public void testInsertDuplicates() {
        bst.insert(10);
        bst.insert(10); // should not increase size
        assertEquals(1, bst.size());
    }

    @Test
    public void testSearchExistingElement() {
        bst.insert(10);
        bst.insert(5);
        assertNotNull(bst.search(5));
    }

    @Test
    public void testSearchNonExistingElement() {
        bst.insert(10);
        assertNull(bst.search(15));
    }

    @Test
    public void testDeleteLeafNode() {
        bst.insert(10);
        bst.insert(5);
        bst.delete(5);
        assertNull(bst.search(5));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(7);
        bst.delete(5);
        assertNull(bst.search(5));
        assertNotNull(bst.search(7));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);
        bst.delete(10);
        assertNotNull(bst.search(12)); // ensure 12 is still present
    }

    @Test
    public void testFindMin() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(3);
        assertEquals(3, bst.findMin());
    }

    @Test
    public void testFindMax() {
        bst.insert(10);
        bst.insert(15);
        bst.insert(20);
        assertEquals(20, bst.findMax());
    }

    @Test
    public void testHeight() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(1, bst.height());
    }

    @Test
    public void testInOrderTraversal() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        List<Integer> expected = Arrays.asList(5, 10, 15);
        assertEquals(expected, bst.inOrderTraversal());
    }

    @Test
    public void testLevelOrderTraversal() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        List<Integer> expected = Arrays.asList(10, 5, 15);
        assertEquals(expected, bst.levelOrderTraversal());
    }

    @Test
    public void testCountNodes() {
        assertEquals(0, bst.countNodes());
        bst.insert(10);
        bst.insert(5);
        assertEquals(2, bst.countNodes());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.insert(10);
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testContains() {
        bst.insert(10);
        assertTrue(bst.contains(10));
        assertFalse(bst.contains(5));
    }

    @Test
    public void testClear() {
        bst.insert(10);
        bst.clear();
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testRemoveNonComparable() {
        assertFalse(bst.remove("test")); // Should return false
    }

    @Test
    public void testSubList() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        List<Integer> expected = Arrays.asList(5, 10);
        assertEquals(expected, bst.subList(0, 2));
    }

    @Test
    public void testToArray() {
        bst.insert(10);
        bst.insert(5);
        Object[] array = bst.toArray();
        assertEquals(5, array[0]);
        assertEquals(10, array[1]);
    }

    @Test
    public void testToArrayGeneric() {
        bst.insert(10);
        bst.insert(5);
        Integer[] array = bst.toArray(new Integer[0]);
        assertEquals(5, array[0]);
        assertEquals(10, array[1]);
    }

    @Test
    public void testAddAll() {
        List<Integer> collection = Arrays.asList(10, 5, 15);
        bst.addAll(collection);
        assertEquals(3, bst.size());
    }

    @Test
    public void testRemoveAll() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        List<Integer> collection = Arrays.asList(5, 15);
        bst.removeAll(collection);
        assertEquals(1, bst.size());
    }

    @Test
    public void testRetainAll() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        List<Integer> collection = Arrays.asList(5);
        bst.retainAll(collection);
        assertEquals(1, bst.size());
        assertTrue(bst.contains(5));
    }

    @Test
    public void testAddAllAtIndex() {
        List<Integer> collection = Arrays.asList(10, 5);
        bst.addAll(0, collection);
        assertEquals(2, bst.size());
    }

    @Test
    public void testIndexOf() {
        bst.add(10);
        bst.add(5);
        assertEquals(0, bst.indexOf(5));
        assertEquals(1, bst.indexOf(10));
    }

    @Test
    public void testLastIndexOf() {
        bst.add(10);
        bst.add(5);
        assertEquals(0, bst.lastIndexOf(5));
        assertEquals(1, bst.lastIndexOf(10));
    }

    @Test
    public void testRemoveByIndexNotSupported() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            bst.remove(0);
        });
        assertEquals("Remove by index is not supported in a BST.", exception.getMessage());
    }

    @Test
    public void testSetNotSupported() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            bst.set(0, 10);
        });
        assertEquals("Not supported yet.", exception.getMessage());
    }

    @Test
    public void testListIteratorNotSupported() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            bst.listIterator();
        });
        assertEquals("Not supported yet.", exception.getMessage());
    }

    @Test
    public void testListIteratorWithIndexNotSupported() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            bst.listIterator(0);
        });
        assertEquals("Not supported yet.", exception.getMessage());
    }
}

