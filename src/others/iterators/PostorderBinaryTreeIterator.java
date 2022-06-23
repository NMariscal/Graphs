package others.iterators;

import javafx.geometry.Pos;
import material.BinaryTree.BinaryTree;
import material.Position;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class PostorderBinaryTreeIterator<E> implements Iterator<Position<E>> {
    // Profudidad = pila
    Deque<Position<E>> stack = new LinkedList<>();
    BinaryTree<E> binaryTree;

    public PostorderBinaryTreeIterator(BinaryTree<E> binaryTree) {
        this.binaryTree = binaryTree;
        if (!binaryTree.isEmpty()){
            goLastInLeft(binaryTree.root());
        }
    }

    public PostorderBinaryTreeIterator(BinaryTree<E> binaryTree, Position<E> position) {
        this.binaryTree = binaryTree;
        if (!binaryTree.isEmpty()){
            goLastInLeft(position);
        }
    }

    private void goLastInLeft(Position<E> position){
        while (binaryTree.hasLeft(position)){
            position = binaryTree.left(position);
            stack.addFirst(position);
        }
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Position<E> next() {
        Position<E> remove = stack.remove();
        while(binaryTree.hasRight(remove)){
            stack.addFirst(binaryTree.right(remove));
            goLastInLeft(binaryTree.right(remove));

        }
        return remove;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
