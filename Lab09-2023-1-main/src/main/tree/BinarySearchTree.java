package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {
    private Node root;

    // Node class
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElemento(root, valor);
    }

    private boolean buscaElemento(Node node, int valor) {
        if (node == null) {
            return false;
        }
        if (valor < node.value) {
            return buscaElemento(node.left, valor);
        } else if (valor > node.value) {
            return buscaElemento(node.right, valor);
        } else {
            return true;
        }
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return minimo(root);
    }

    private int minimo(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return maximo(root);
    }

    private int maximo(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElemento(root, valor);
    }

    private Node insereElemento(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }
        if (valor < node.value) {
            node.left = insereElemento(node.left, valor);
        } else if (valor > node.value) {
            node.right = insereElemento(node.right, valor);
        }
        return node;
    }

    @Override
    public void remove(int valor) {
        root = remove(root, valor);
    }

    private Node remove(Node node, int valor) {
        if (node == null) {
            return null;
        }

        if (valor < node.value) {
            node.left = remove(node.left, valor);
        } else if (valor > node.value) {
            node.right = remove(node.right, valor);
        } else {
            
            if (node.left == null && node.right == null) {
              
                return null;
            } else if (node.left == null) {
               
                return node.right;
            } else if (node.right == null) {
                
                return node.left;
            } else {
              
                int minValue = minimo(node.right);
                node.value = minValue;
                node.right = remove(node.right, minValue);
            }
        }
        return node;
    }

    @Override
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdem(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdem(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrdem(node.left, result);
            preOrdem(node.right, result);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdem(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void emOrdem(Node node, List<Integer> result) {
        if (node != null) {
            emOrdem(node.left, result);
            result.add(node.value);
            emOrdem(node.right, result);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdem(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void posOrdem(Node node, List<Integer> result) {
        if (node != null) {
            posOrdem(node.left, result);
            posOrdem(node.right, result);
            result.add(node.value);
        }
    }
}