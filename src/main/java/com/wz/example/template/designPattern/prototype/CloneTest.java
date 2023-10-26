package com.wz.example.template.designPattern.prototype;

/**
 * 浅拷贝
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Caregiver harry = new Caregiver("harry", 18, 1);
        Caregiver tom = new Caregiver("tom", 25, 2);


        Sheep sheep = new Sheep("原型羊", 1, 1, harry);
        Sheep dolly = sheep.clone();

        System.out.println(sheep);
        System.out.println(dolly);

        System.out.println("---------------");

        dolly.setName("dolly");
        dolly.setAge(18);
        dolly.getCaregiver().setAge(38);

        System.out.println(sheep);
        System.out.println(dolly);

//        Employee original = new Employee("abc",15);
//        Employee copy = (Employee) original.clone();
//        System.out.println(original.m.money);
//        System.out.println(copy .m.money) ;
//        System.out.println("================");
//        copy.m.money = 99;
//        System.out.println(original.m.money);
//        System.out.println(copy.m.money);

    }
}
