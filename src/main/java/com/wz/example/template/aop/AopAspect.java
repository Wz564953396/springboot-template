package com.wz.example.template.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {

    /**
     * execution()：用于匹配方法执行的连接点
     * args(): 用于匹配当前执行的方法传入的参数为指定类型的执行方法
     * this(): 用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
     * target(): 用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
     * within(): 用于匹配指定类型、包内的方法执行；
     * @args(): 于匹配当前执行的方法传入的参数持有指定注解的执行；
     * @target(): 用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
     * @within(): 用于匹配所以持有指定注解类型内的方法；
     * @annotation: 用于匹配当前执行方法持有指定注解的方法；
     *
     *
     * execution():
     * 所有的方法 execution(* *(..))   --->   execution(修饰符+返回值 包.类.方法名(参数类型列表))
     * 第一个 * 代表 修饰符 返回值
     * 第二个 * 代表 包.类.方法名
     *  ()     代表 参数列表。非java.lang包中的类型，必须要写全限定名
     *  ..     代表 对于参数没有要求
     *
     *  指定方法login(String s1, String s2)方法   execution(* login(String, String))
     *  指定方法login(String s1, ..)方法          execution(* login(String, ..))
     *
     *  指定某个包下某个类下的所有方法                execution(* com.wz.example.template.aop.AopService.*(..))
     *  指定所有包下某个类下的所有方法                execution(* *..AopService.*(..))
     *  指定某个包下的所有方法（不能是子包）           execution(* com.wz.example.template.*.*(..))
     *  指定某个包下的所有方法（可以是子包）           execution(* com.wz.example.template..*.*(..))
     *
     *  args():
     *  切入点：方法参数必须是两个字符串类型的参数      args(String, String)  或者  execution(* *(String, String))
     *
     *  within():
     *  指定某个类、包 作为切入点                   within(*..AopService)
     *  指定某个类 作为切入点                      within(com.wz.example.template.aop..*)
     *
     * @annotation():
     * 指定注解标记的方法 作为切入嗲                 @annotation(com.wz.example.template.aop.LogAnnotation)
     *
     *
     * 切入点函数的逻辑运算（整合多个切入点函数一起配合工作，进而完成更为复杂的切入需求）
     *
     * 1.and (不能同时使用同类型的切入点函数)
     * 同事指定所有login()方法 并且 两个String参数 的方法作为切入点
     *                                          execution(* login(..)) and args(String, String)
     *
     */
    @Pointcut("@annotation(com.wz.example.template.aop.LogAnnotation), execution(* com.wz.example.template.aop.AopService.*(..))")
    public void pointcut() {

    }

    @Before()
    public Object before() {
        System.out.println("Aspect before ...");
        return null;
    }

    @Around()
    public Object around() {
        System.out.println("Aspect around ...");
        return null;
    }

}
