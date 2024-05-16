public class Node {
    private String data;
    private Node parent;
    private Node right;
    private Node left;
    private int balanceFactor;

    public Node(String data) {
        this.data = data;
        right = null;
        left = null;
        parent = null;
        this.balanceFactor = 0;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(String data) {
        this.data = data;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setBalanceFactor(int balanceFactor){
        this.balanceFactor = balanceFactor;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public String getValue() { // Alterado para retornar int
        return data;
    }

    public Node getParent() {
        return parent;
    }

    public Node getNode(Node current, String data) {
        if (current == null) {
            return null;
        }

        if (current.getValue().equals(data)) {
            return current;
        }
        Node foundNode = getNode(current.getLeft(), data);

        if (foundNode == null) {
            foundNode = getNode(current.getRight(), data);
        }

        return foundNode;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        if (right == null && left == null) {
            return true;
        }
        return false;
    }

    public int getBalanceFactor(Node node){
        if (node == null) {
            return 0;
        }
        return getNodeHeight(node.getLeft(), 0) - getNodeHeight(node.getRight(), 0);
    }

    public int updateBalanceFactor() {
        if (this == null) {
            return 0;
        }
        this.balanceFactor = getNodeHeight(this.getLeft(), 0) - getNodeHeight(this.getRight(), 0);
        return this.balanceFactor;
    }


    public int getNodeHeight(Node node, int height) {
        if (node == null) {
            return height;
        }

        int leftHeight = getNodeHeight(node.getLeft(), height + 1);
        int rightHeight = getNodeHeight(node.getRight(), height + 1);

        return Math.max(leftHeight, rightHeight);
    }

    public int getNodeDegree(Node node) {
        int degree = 0;
        if (node == null) {
            return 0;
        }
        if (node.getLeft() != null) {
            degree++;
        }
        if (node.getRight() != null) {
            degree++;
        }
        return degree;
    }

    public int getLevel(Node node, int level) {
        if (node == null) {
            return 0;
        }
        if (node.getValue().equals(data)) {
            return level;
        }
        int downlevel = getLevel(node.left, level + 1);
        if (downlevel != 0) {
            return downlevel;
        }
        downlevel = getLevel(node.right, level + 1);
        return downlevel;
    }
}
