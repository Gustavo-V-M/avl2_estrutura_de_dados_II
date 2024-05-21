import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isEmpty(Node node) {
        if (node == null) {
            return true;
        }
        return false;
    }

    public int getDegreeRoot(Node root, int degree) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() != null) {
            degree++;
        }
        if (root.getRight() != null) {
            degree++;
        }
        return degree;
    }

    public int getHeightRoot(Node root) {
        if (root == null)
            return 0;
        else {
            int leftHeight = getHeightRoot(root.getLeft());
            int rightHeight = getHeightRoot(root.getRight());

            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }
    }

    public int getHeight() {
        return getHeightRoot(this.root) - 1;
    }

    public int getDepth(Node root) {
        if (root == null)
            return 0;
        else {
            int leftDepth = getDepth(root.getLeft());
            int rightDepth = getDepth(root.getRight());

            if (leftDepth > rightDepth)
                return (leftDepth + 1);
            else
                return (rightDepth + 1);
        }
    }

    private Node addRecursive(Node root, int id, String data, TokenType type, Node parent) {

        if (root == null) {
            Token toAdd = new Token(type, data, id);
            root = new Node(toAdd);
            root.setParent(parent);
            return root;
        }

        if (id < root.getValue().getId()) {
            root.setLeft(addRecursive(root.getLeft(), id, data, type, root));
        } else if (id > root.getValue().getId()) {
            root.setRight(addRecursive(root.getRight(), id, data, type, root));
        }

        return root;
    }

    public boolean add(String value, TokenType type, int id) {
        if (searchNode(this.root, id) == null) {
            root = addRecursive(root, id, value, type, null);
            return true;
        } else {
            System.out.println("No " + value + " ja  existe na arvore");
            return false;
        }
    }

    public void clear() {
        root = null;
    }

    public Node removeNode(Node root, int id) {
        if (root == null) {
            return null;
        }

        if (id < root.getValue().getId()) {
            root.setLeft(removeNode(root.getLeft(), id));
        } else if (id > root.getValue().getId()) {
            root.setRight(removeNode(root.getRight(), id));
        } else {
            // Caso nó a ser removido seja encontrado
            if (root.getLeft() == null && root.getRight() == null) {
                return null; // Nó não tem filhos
            } else if (root.getLeft() == null) {
                Node rightChild = root.getRight();
                if (rightChild != null) {
                    rightChild.setParent(root.getParent()); // Atualiza o pai do filho direito
                }
                return rightChild;
            } else if (root.getRight() == null) {
                Node leftChild = root.getLeft();
                if (leftChild != null) {
                    leftChild.setParent(root.getParent()); // Atualiza o pai do filho esquerdo
                }
                return leftChild;
            } else {
                // Nó tem dois filhos
                Node successor = nodeMin(root.getRight());
                root.setValue(successor.getValue());
                // Remove o sucessor da sua posição original e atualiza as referências de pai conforme necessário
                root.setRight(removeNode(root.getRight(), successor.getValue().getId()));
            }
        }
        return root;
    }

    // Este método precisa assegurar que a referência do pai do menor nó seja corretamente atualizada, se necessário

    public void treeInfo() {
        printInOrderRec(root);
    }

    // Método recursivo para realizar a travessia em ordem
    private void printInOrderRec(Node node) {
        if (node != null) {
            // Visita o filho à esquerda


            printInOrderRec(node.getLeft());

            // Visita o nó atual
            printNodeInfo(node);

            // Visita o filho à direita
            printInOrderRec(node.getRight());


        }
    }

    // Método para imprimir as informações do nó
    private void printNodeInfo(Node node) {
        // Exemplo: Imprime o valor do nó e informações do pai se disponível
        System.out.println("Value: " + node.getValue());
        if (node.getParent() != null) {
            System.out.println("    Parent: " + node.getParent().getValue());
        } else {
            System.out.println("    Parent: null");
        }
        if (node.getLeft() != null) {
            System.out.println("    Left Child: " + node.getLeft().getValue());
        } else {
            System.out.println("    Left Child: null");
        }
        // Verifica e imprime o filho da direita
        if (node.getRight() != null) {
            System.out.println("    Right Child: " + node.getRight().getValue());
        } else {
            System.out.println("    Right Child: null");
        }
        System.out.println("    é folha? " + node.isLeaf());
        System.out.println("    é raiz? " + node.isRoot());
        System.out.println("    grau:" + node.getNodeDegree(this.root));
        System.out.println("    nivel:" + node.getLevel(this.root,0));
        System.out.println("    Altura:" + node.getNodeHeight(node, 0));
    }

    public Node searchNode(Node current, int id) {
        if (current == null)
            return null;
        if (current.getValue().getId() == id) {
            return current;
        }
        Node node = searchNode(current.getLeft(), id);
        if (node == null) {
            node = searchNode(current.getRight(), id);
        }
        return node;
    }


    public void preOrderTraversal(Node node) {
        if (!isEmpty(node)) {
            System.out.println(node.getValue());
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    public void postOrderTraversal(Node node) {
        if (!isEmpty(node)) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.println(node.getValue());
        }
    }

    public void inOrderTraversal(Node node) {
        if (!isEmpty(node)) {
            inOrderTraversal(node.getLeft());
            System.out.println(node.getValue());
            inOrderTraversal(node.getRight());
        }
    }

    public Node findSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() != null) {
            return nodeMin(node.getRight());
        }
        Node successor = null;
        Node current = root;
        while (current != node) {
            if (node.getValue().getId() > current.getValue().getId()) {
                successor = current;
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return successor;
    }

    public Node findPredecessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() != null) {
            return nodeMin(node.getLeft());
        }
        Node predecessor = null;
        Node current = root;
        while (current != node) {
            if (node.getValue().getId() < current.getValue().getId()) {
                predecessor = current;
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return predecessor;
    }

    public boolean remove(int id) {
        if (searchNode(this.root, id) != null) {
            removeNode(this.root, id);
            return true;
        } else {
            System.out.println("No " + id + " nao existe na arvore");
            return false;
        }
    }

    public void printLevelOrder(Node root) {

        if (root == null)
            return;

        Queue<Node> q = new LinkedList<Node>();

        q.add(root);

        while (true) {
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;
            while (nodeCount > 0) {
                Node node = q.peek();
                System.out.print(node.getValue() + " ");
                q.remove();
                if (node.getLeft() != null)
                    q.add(node.getLeft());
                if (node.getRight() != null)
                    q.add(node.getRight());
                nodeCount--;
            }
            System.out.println("\n");
        }
    }

    public Node nodeMax(Node max) {
        if (max == null) {
            return null;
        }
        while (max.getRight() != null) {
            max = max.getRight();
        }
        return max;
    }

    public Node nodeMin(Node min) {
        if (min == null) {
            return null;
        }
        while (min.getLeft() != null) {
            min = min.getLeft();
        }
        return min;
    }
}
