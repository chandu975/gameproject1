public class BinarySearchTree {
    private Node root;
    public BinarySearchTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }
    public boolean find(int key) {
        Node curr = root;
        while (curr != null) {
            if (curr.data == key) {
                return true;
            } else if (curr.data > key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }


    public int get(int key) throws Exception{
        Node curr= root;
        while (curr != null) {
            if (curr.data == key) {
                return curr.value;
            } else if (curr.data > key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        throw new Exception("Key not found!");
    }

    public void set(int key, int value) {
        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
        } else {
            Node curr = root;
            Node parent;
            while (true) {
                parent = curr;
                if (key < curr.data) {
                    curr = curr.left;
                    if (curr == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    curr = curr.right;
                    if (curr== null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public int size() {
        return sizeHelper(root);
    }
    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;
        } else {
            return (sizeHelper(node.left) + 1 + sizeHelper(node.right));
        }
    }


    public int get(Object key) throws Exception {
        if (key instanceof Integer) {
            return get((int) key);
        } else {
            throw new Exception("Key must be an Integer");
        }
    }

    public void insert(Object key, int value) throws Exception {
        if (key instanceof Integer) {
            set((int) key, value);
        } else {
            throw new Exception("Key must be an Integer");
        }
    }


    public int _len_() {
        return size();
    }


    public boolean _contains_(Object key) throws Exception {
        if (key instanceof Integer) {
            return find((int) key);
        } else {
            throw new Exception("Key must be an Integer");
        }
    }

    public void insert(int i) {
    }

    private class Node {
        private int data;
        private int value;
        private Node left;
        private Node right;

        public Node(int data, int value) {
            this.data = data;
            this.value = value;
        }
    }
}
class Main {
    public static void main(String[] args) throws Exception {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);


    }
}