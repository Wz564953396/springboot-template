package com.wz.example.template.designPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 非叶子节点
 */
public class DistrictNode extends Node{
    private List<Node> children = new ArrayList<>();

    public DistrictNode(String name) {
        super(name);
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node node){
        children.add(node);
    }

    public void delChild(int i){
        children.remove(i);
    }


}
