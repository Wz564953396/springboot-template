package com.wz.example.template.designPattern.composite;

import java.util.List;

/**
 * Created by Kevin on 10/29 029.
 */
public abstract class Node {
    private String name;

    public Node(String name){
        this.name = name;
    }

    public abstract List<Node> getChildren();

    public String getName() {
        return name;
    }
}
