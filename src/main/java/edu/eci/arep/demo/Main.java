package edu.eci.arep.demo;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        // Insertar elementos
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(3);
        bst.add(7);
        bst.add(12);
        bst.add(18);

        // Recorrido en orden
        System.out.println("In-order Traversal: " + bst.inOrderTraversal());

        // Buscar elementos
        System.out.println("Buscar 7: " + (bst.search(7) != null ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 20: " + (bst.search(20) != null ? "Encontrado" : "No encontrado"));

        // Encontrar mínimo y máximo
        System.out.println("Valor mínimo: " + bst.findMin());
        System.out.println("Valor máximo: " + bst.findMax());

        // Altura del árbol
        System.out.println("Altura del árbol: " + bst.height());

        // Recorrido por niveles
        System.out.println("Level-order Traversal: " + bst.levelOrderTraversal());

        // Eliminar un elemento
        bst.remove(10);
        System.out.println("Después de eliminar 10, In-order Traversal: " + bst.inOrderTraversal());

        // Contar nodos
        System.out.println("Número de nodos en el árbol: " + bst.countNodes());

        // Limpiar el árbol
        bst.clear();
        System.out.println("Después de limpiar, el árbol está vacío: " + bst.isEmpty());
    }
}
