package com.wz.example.template.designPattern.decorator;


/**
 * 装饰者模式：
 *      动态的将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案。
 * 优点：
 *      拓展某个类的功能，附加功能
 *      动态地给一个对象增加功能，这些功能可以动态地撤销
 *      需要给一批兄弟类进行改装或加装功能
 * 缺点：
 *      装饰的细节不宜过多，否则很难把控执行公用方法的切入点（比如一个类对原类进行装饰后，第二个类就只能在前一个类的基础上再装饰，这样就还需要考虑第一次装饰后的组合结果）
 *      因为装饰的结构是由内向外，如果内层出现修改，就会影响外层的结果（有可能埋下大坑）。
 *
 * 在 Java 8中，装饰者设计模式可以在多个地方找到应用。以下是一些使用装饰者模式的常见示例：
 *      I/O流：
 *      Java 8中的java.io包中的InputStream和OutputStream类使用了装饰者模式。
 *      例如，BufferedInputStream和BufferedOutputStream类就是装饰者，它们包装了底层的输入流或输出流，并提供了缓冲的功能。
 *
 *      函数式接口的默认方法：
 *      Java 8引入了函数式接口，这些接口可以有默认方法。默认方法允许在接口中提供默认的行为实现，并且可以通过继承或实现接口的方式进行组合。
 *      这种方式类似于装饰者模式，其中接口定义了基本的行为，而默认方法提供了额外的功能。
 *
 *      日志框架：
 *      许多Java日志框架（如Log4j、Logback）使用装饰者模式来实现日志记录器的层次结构。
 *      通过使用装饰者模式，可以动态地添加、修改或扩展日志记录器的行为，而无需修改原始的日志记录器。
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Cappuccino cappuccino = new Cappuccino();

        cappuccino.make();
    }
}
