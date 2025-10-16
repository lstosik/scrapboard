package net.purevirtual.scrapboard.algs.map;

/**
 * @url http://en.wikipedia.org/wiki/Ternary_search_tree
 */
public class TernaryTrie<Value> {

    Node<Value> root;

    public Value get(Object key) {
        return get((String) key, root, 0);
    }

    public Value get(String key, Node<Value> node, int index) {
        char curChar = key.charAt(index);
        if (curChar < node.keyChar && node.left != null) {
            return get(key, node.left, index);
        } else if (curChar > node.keyChar && node.right != null) {
            return get(key, node.right, index);
        }

        if (index == key.length() - 1) {
            return node.value;
        } else if (node.middle != null) {
            return get(key, node.middle, index + 1);
        }
        return null;
    }
    
    int getDepth(String key) {
        return getDepth(key, root, 0);
    }
    
    private int getDepth(String key, Node<Value> node, int index) {
        char curChar = key.charAt(index);
        if (curChar < node.keyChar && node.left != null) {
            return 1+getDepth(key, node.left, index);
        } else if (curChar > node.keyChar && node.right != null) {
            return 1+getDepth(key, node.right, index);
        }

        if (index == key.length() - 1) {
            return 0;
        } else if (node.middle != null) {
            return 1+getDepth(key, node.middle, index + 1);
        }
        throw new IllegalArgumentException();
    }

    public Value put(String key, Value value) {
        if (root == null) {
            root = new Node<>();
            root.keyChar = key.charAt(0);
        }
        put(key, value, root, 0);
        return null;
    }

    private Value put(String key, Value value, Node<Value> node, int index) {
        char curChar = key.charAt(index);
        if (curChar < node.keyChar) {
            if (node.left == null) {
                node.left = new Node<>(curChar);
            }
            return put(key, value, node.left, index);
        } else if (curChar > node.keyChar) {
            if (node.right == null) {
                node.right = new Node<>(curChar);
            }
            return put(key, value, node.right, index);
        }

        if (index == key.length() - 1) {
            Value oldVal = node.value;
            node.value = value;
            return oldVal;
        } else {
            if (node.middle == null) {
                node.middle = new Node<>(key.charAt(index + 1));
            }
            return put(key, value, node.middle, index + 1);
        }

    }





    private static class Node<Value> {

        char keyChar;
        Value value;
        Node<Value> left;
        Node<Value> middle;
        Node<Value> right;

        public Node() {
        }

        public Node(char keyChar) {
            this.keyChar = keyChar;
        }
    }
}
