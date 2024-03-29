package com.wz.example.template.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RequestMappingInfoHandlerMapping处理的请求，装配到handlerMapping后，执行afterPropertiesSet()方法
 * AbstractHandlerMethodMapping.afterPropertiesSet() -> isHandler(beanType)
 * 判断通过的handler，装配到AbstractHandlerMethodMapping.mappingRegistry 中
 * 判断方式：AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
 * 	       AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class)
 */

/**
 * 当接收到请求时，会根据请求路径找到Handler，然后有一个适配的过程，SpringMvc中默认有四个适配器
 * 1.RequestMappingHandlerAdapter: 如果 Handler是 HandlerMethod对象，后面就用他来执行请求
 * 2.HandlerFunctionAdapter: 如果 Handler是 HandlerFunction对象，后面就用他来执行请求
 * 3.HtpRequestHandlerAdapter: 如果 Handler是 HttpRequestHandler对象，后面就用他来执行请求
 * 4.SimpleControllerHandlerAdapter: 如果 Handler是 Controller对象，后面就用他来执行请求
 */

@RequestMapping("/mvc")
@Controller
public class MvcTest1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(MvcTest1Controller.class);


    /**
     *
     * @return
     */
    @RequestMapping("/test1")
    public String test1() {
        System.out.println("test1");
        return "";
    }
}
