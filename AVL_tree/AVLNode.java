package org.example.AVL_tree;

// Класс, представляющий узел АВЛ-дерева
class AVLNode {
    int key;
    int height;
    AVLNode left;
    AVLNode right;

    AVLNode(int key) {
        this.key = key;
        this.height = 1; // Новый узел всегда добавляется как лист
    }
}