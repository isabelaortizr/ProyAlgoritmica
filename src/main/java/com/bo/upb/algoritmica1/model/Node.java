package com.bo.upb.algoritmica1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@ToString
public class Node {

    private int value;
    private int posX;
    private int posY;

    private Node left, right;

    private int fe; // factor de equilibrio

    public Node(int value) {
        this.value = value;
    }

    /**
     * Función para indicar si el nodo es hoja
     * @return
     */
    public boolean isLeaf() {
        return getLeft() == null && getRight() == null;
    }

    public boolean hasTwoSon() {
        return getLeft() != null && getRight() != null;
    }

    public boolean areValuesChildren(int nodeValue1, int nodeValue2) {
        return (getLeft().getValue() == nodeValue1 && getRight().getValue() == nodeValue2)
                || (getLeft().getValue() == nodeValue2 && getRight().getValue() == nodeValue1);
    }

    public List<Node> getChildren() {
        if (isLeaf())
            return new ArrayList<>(0);

        List<Node> children = new ArrayList<>(2);
        if (left != null)
            children.add(left);
        if (right != null)
            children.add(right);

        return children;
    }
}
