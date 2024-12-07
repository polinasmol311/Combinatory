package org.example.AVL_tree;

// Класс для реализации АВЛ-дерева
class AVLTree {
    private AVLNode root;

    // Получение высоты узла
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // Получение баланса узла
    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Правый поворот поддерева с заданным корнем
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T = x.right;

        // Поворот
        x.right = y;
        y.left = T;

        // Обновление высот
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // Новый корень поддерева
    }

    // Левый поворот поддерева с заданным корнем
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T = y.left;

        // Поворот
        y.left = x;
        x.right = T;

        // Обновление высот
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // Новый корень поддерева
    }

    // Вставка ключа в дерево
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private AVLNode insertRec(AVLNode node, int key) {
        // Обычная вставка в бинарное дерево поиска
        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insertRec(node.left, key);
        else if (key > node.key)
            node.right = insertRec(node.right, key);
        else // Дубликаты не допускаются
            return node;

        // Обновление высоты текущего узла
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Проверка баланса текущего узла
        int balance = getBalance(node);

        // Левый-левый случай
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        // Правый-правый случай
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        // Левый-правый случай
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Правый-левый случай
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node; // Узел остаётся без изменений
    }

    // Удаление ключа из дерева
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private AVLNode deleteRec(AVLNode node, int key) {
        // Обычное удаление в бинарном дереве поиска
        if (node == null)
            return node;

        if (key < node.key)
            node.left = deleteRec(node.left, key);
        else if (key > node.key)
            node.right = deleteRec(node.right, key);
        else {
            // Узел с одним или без потомков
            if ((node.left == null) || (node.right == null)) {
                AVLNode temp = node.left != null ? node.left : node.right;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp; // Замена узла
            } else {
                // Узел с двумя потомками: получение минимального узла из правого поддерева
                AVLNode temp = getMinValueNode(node.right);

                // Замена значения узла на минимальный
                node.key = temp.key;

                // Удаление минимального узла из правого поддерева
                node.right = deleteRec(node.right, temp.key);
            }
        }

        // Если дерево имеет только один узел
        if (node == null)
            return node;

        // Обновление высоты текущего узла
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Проверка баланса текущего узла
        int balance = getBalance(node);

        // Левый-левый случай
        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        // Левый-правый случай
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Правый-правый случай
        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);

        // Правый-левый случай
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode getMinValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Поиск ключа в дереве
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(AVLNode node, int key) {
        if (node == null)
            return false;
        if (key < node.key)
            return searchRec(node.left, key);
        else if (key > node.key)
            return searchRec(node.right, key);
        return true;
    }

    // Печать дерева (обход в порядке)
    public void printInOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(AVLNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.key + " ");
            inOrderRec(node.right);
        }
    }
}

