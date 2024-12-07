package org.example.AVL_tree;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Пример операций с АВЛ-деревом
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Обход в порядке после вставки элементов:");
        tree.printInOrder();

        tree.delete(30);
        System.out.println("Обход в порядке после удаления элемента 30:");
        tree.printInOrder();

        System.out.println("Поиск элемента 40: " + tree.search(40));
        System.out.println("Поиск элемента 100: " + tree.search(100));
    }
}
