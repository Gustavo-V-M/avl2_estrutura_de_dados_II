import java.util.LinkedList;
import java.util.Queue;

public class AvlTree {
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

    private Node addRecursive(Node root, String data, Node parent) {

        if (root == null) {
            root = new Node(data);
            root.setParent(parent);
            return root;
        }

        if (data.compareTo(root.getValue()) < 0) {
            root.setLeft(addRecursive(root.getLeft(), data, root)); // Passa root como parent do nó esquerdo
        } else if (data.compareTo(root.getValue()) > 0) {
            root.setRight(addRecursive(root.getRight(), data, root)); // Passa root como parent do nó direito
        }

        return root;
    }

    public boolean add(String value) {
        if (searchNode(this.root, value) == null) {
            root = addRecursive(root, value, null);
            return true;
        } else {
            System.out.println("No " + value + " ja  existe na arvore");
            return false;
        }
    }

    public void clear() {
        root = null;
    }

    public Node removeNode(Node root, String value) {
        if (root == null) {
            return null;
        }

        if (value.compareTo(root.getValue()) < 0) {
            root.setLeft(removeNode(root.getLeft(), value));
        } else if (value.compareTo(root.getValue()) > 0) {
            root.setRight(removeNode(root.getRight(), value));
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
                root.setRight(removeNode(root.getRight(), successor.getValue()));
            }
        }
        balance(root.getNode(root, value));
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
        System.out.println("    nivel:" + node.getLevel(this.root, 0));
        System.out.println("    Altura:" + node.getNodeHeight(node, 0));
    }

    public Node searchNode(Node current, String value) {
        if (current == null)
            return null;
        if (current.getValue().equals(value)) {
            return current;
        }
        Node node = searchNode(current.getLeft(), value);
        if (node == null) {
            node = searchNode(current.getRight(), value);
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

    private Node rotateLeft(Node root){
        Node aux = root.getRight();
        root.setRight(aux.getLeft());
        if (aux.getLeft() != null) {
            aux.getLeft().setParent(root);
        }
        aux.setParent(root.getParent());
        if (root.getParent() == null) {
            setRoot(aux);
        } else if (root == root.getParent().getLeft()) {
            root.getParent().setLeft(aux);
        } else {
            root.getParent().setRight(aux);
        }
        aux.setLeft(root);
        root.setParent(aux);
        return aux;
    }
    public Node callRotateLeft(Node root) {
        return rotateLeft(root);
    }

    private Node rotateRight(Node root){
        Node aux = root.getLeft();
        root.setLeft(aux.getRight());
        if (aux.getRight() != null) {
            aux.getRight().setParent(root);
        }
        aux.setParent(root.getParent());
        if (root.getParent() == null) {
            setRoot(aux);
        } else if (root == root.getParent().getRight()) {
            root.getParent().setRight(aux);
        } else {
            root.getParent().setLeft(aux);
        }
        aux.setRight(root);
        root.setParent(aux);
        return aux;
    }
    public Node callRotateRight(Node root) {
        return rotateRight(root);
    }

    private Node rotateLeftRight(Node root) {
        root.setLeft(rotateLeft(root.getLeft()));
        return rotateRight(root);
    }
    public Node callRotateLeftRight(Node root) {
        return rotateLeftRight(root);
    }

    private Node rotateRightLeft(Node root){
        root.setRight(rotateRight(root.getRight()));
        return rotateLeft(root);
    }
    public Node callRotateRightLeft(Node root) {
        return rotateRightLeft(root);
    }


    public boolean balance(Node root) {
        int balanceFactor = root.getBalanceFactor(root);

        System.out.println("Fator de balanceamento do nó " + root.getValue() + ": " + balanceFactor);
        if (balanceFactor > 1) {
            if (root.getLeft().getBalanceFactor(root.getLeft()) >= 0) {
                rotateRight(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação LL");
                return true;
            }
            else {
                rotateLeftRight(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação LR");
                return true;
            }
        }
        else if (balanceFactor < -1) {
            if (root.getRight().getBalanceFactor(root.getRight()) <= 0) {
                rotateLeft(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação RR");
                return true;
            }
            else {
                rotateRightLeft(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação RL");
                return true;
            }
        }
        return false;
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
            if (node.getValue().compareTo(current.getValue()) < 0) {
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
            if (node.getValue().compareTo(current.getValue()) < 0) {
                predecessor = current;
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return predecessor;
    }


    public boolean remove(String value) {
        if (searchNode(this.root, value) != null) {
            removeNode(this.root, value);
            return true;
        } else {
            System.out.println("No " + value + " nao existe na arvore");
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