package com.wz.example.template.designPattern.composite;

import java.util.List;

/**
 * 叶子节点
 */
public class LeafNode extends Node{

    public LeafNode(String name) {
        super(name);
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }

}
