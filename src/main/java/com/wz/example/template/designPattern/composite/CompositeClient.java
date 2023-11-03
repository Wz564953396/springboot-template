package com.wz.example.template.designPattern.composite;

/**
 * 组合模式：
 *      将多个对象组合成树形结构，以此结构来表示“整体-部分”之间的层次关系。
 *      1、Component：组合模式中的“根节点”，可以是接口、抽象类、普通类，该类中定义了其子类的所有共性内容，并且该类中还存在着用于访问和管理它子部件的方法。
 *      2、LeafNode：组合中的叶子节点，也就是最末端的节点，该节点下不会再有子节点。
 *      3、Composite：非叶子节点，它的作用是存储子部件，并且在Composite中实现了对子部件的相关操作。
 *
 * 省市县目录组织结构
 */
public class CompositeClient {
    public static void main(String[] args){
        sendFruit();
    }

    public static void sendFruit(){

        //根目录
        DistrictNode root = new DistrictNode("根");

        //一线目录
        root.addChild(new DistrictNode("上海"));
        root.addChild(new DistrictNode("天津"));
        DistrictNode districtNode = new DistrictNode("北京");
        root.addChild(districtNode);

        //二级目录
        districtNode.addChild(new DistrictNode("海淀区"));
        districtNode.addChild(new DistrictNode("西城区"));
        DistrictNode districtNode2 = new DistrictNode("朝阳区");
        districtNode.addChild(districtNode2);

        //三级目录
        districtNode2.addChild(new LeafNode("三里屯"));
        districtNode2.addChild(new LeafNode("朝阳外街"));


    }



}
