import java.util.ArrayList;

public class BST<T extends Comparable<T>> {
    Node rootNode;

    private class Node {
        T key;
        Node leftNode;
        Node rightNode;
        int size;

        public Node(T key) {
            this.key = key;
            this.leftNode = null;
            this.rightNode = null;
            this.size = 1;
        }
    }

    public BST() {
        this.rootNode = null;
    }

    public Node search(T key) {
        return search(this.rootNode, key);
    }

    private Node search(Node rootNode, T key) {
        if (rootNode == null) {
            return null;
        }
        if (rootNode.key.compareTo(key) > 0) {
            return search(rootNode.leftNode, key);
        } else if (rootNode.key.compareTo(key) < 0) {
            return search(rootNode.rightNode, key);
        }
        return rootNode;
    }

    public Node insert(T key) {
        this.rootNode = insert(this.rootNode, key);
        return this.rootNode;
    }

    private Node insert(Node rootNode, T key) {
        if (rootNode == null) {
            return new Node(key);
        }
        if (rootNode.key.compareTo(key) > 0) {
            rootNode.leftNode = insert(rootNode.leftNode, key);
        } else if (rootNode.key.compareTo(key) < 0) {
            rootNode.rightNode = insert(rootNode.rightNode, key);
        }

        int leftSize, rightSize;
        if (rootNode.leftNode == null) {
            leftSize = 0;
        } else {
            leftSize = rootNode.leftNode.size;
        }
        if (rootNode.rightNode == null) {
            rightSize = 0;
        } else {
            rightSize = rootNode.rightNode.size;
        }
        rootNode.size = leftSize + rightSize + 1;
        return rootNode;
    }

    public Node delete(T key) {
        this.rootNode = delete(this.rootNode, key);
        return this.rootNode;
    }

    private Node delete(Node rootNode, T key) {
        if (rootNode.key.compareTo(key) > 0) {
            rootNode.leftNode = delete(rootNode.leftNode, key);
        } else if (rootNode.key.compareTo(key) < 0) {
            rootNode.rightNode = delete(rootNode.rightNode, key);
        } else {
            if (rootNode.leftNode == null && rootNode.rightNode == null) {
                return null;
            } else if (rootNode.leftNode == null) {
                rootNode = rootNode.rightNode;
            } else if (rootNode.rightNode == null) {
                rootNode = rootNode.leftNode;
            } else {
                Node minNode = minNode(rootNode.rightNode);
                minNode.rightNode = deleteMin(rootNode.rightNode);
                minNode.leftNode = rootNode.leftNode;
                rootNode = minNode;
            }
        }
        int leftSize, rightSize;
        if (rootNode.leftNode == null) {
            leftSize = 0;
        } else {
            leftSize = rootNode.leftNode.size;
        }
        if (rootNode.rightNode == null) {
            rightSize = 0;
        } else {
            rightSize = rootNode.rightNode.size;
        }
        rootNode.size = leftSize + rightSize + 1;
        return rootNode;
    }

    private Node deleteMin(Node rootNode) {
        if (rootNode.leftNode == null) {
            return null;
        }
        rootNode.leftNode = deleteMin(rootNode.leftNode);

        int leftSize, rightSize;
        if (rootNode.leftNode == null) {
            leftSize = 0;
        } else {
            leftSize = rootNode.leftNode.size;
        }
        if (rootNode.rightNode == null) {
            rightSize = 0;
        } else {
            rightSize = rootNode.rightNode.size;
        }
        rootNode.size = leftSize + rightSize + 1;
        return rootNode;
    }

    private Node minNode(Node rootNode) {
        if (rootNode.leftNode == null) {
            return rootNode;
        }
        return minNode(rootNode.leftNode);
    }

    public void printInOrder() {
        printInOrder(this.rootNode);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.leftNode);
        System.out.print(node.key + ", ");
        printInOrder(node.rightNode);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] keys = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
        for (int i = 0; i < keys.length; i++) {
            bst.insert(keys[i]);
        }
        bst.printInOrder();
        System.out.println("If the output is [1, 2, ... , 15], then the binary search tree is correct.");
        System.out.println("search(8) returns " + bst.search(8).key);
        System.out.println("search(10) returns " + bst.search(10).key);

        bst.delete(8);
        bst.printInOrder();
        System.out.println("If the output is [1, 2, ... ,7, 9, 10, ... , 15], then the binary search tree is correct.");
        System.out.println("search(8) returns " + bst.search(8));
        System.out.println("search(10) returns " + bst.search(10).key);
    }
}