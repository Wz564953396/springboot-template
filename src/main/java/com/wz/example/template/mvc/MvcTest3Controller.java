package com.wz.example.template.mvc;

import com.wz.example.template.resposne.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * RequestMappingHandlerAdapter.afterPropertiesSet()
 * 扫描带有 @ControllerAdvice 的类
 * 扫描其中的方法：不带有 @RequestMapping 但是带有 @ModelAttribute 的方法 或者是 带有 @InitBinder 的方法
 *      	public static final MethodFilter MODEL_ATTRIBUTE_METHODS = method ->
 * 			(!AnnotatedElementUtils.hasAnnotation(method, RequestMapping.class) &&
 * 					AnnotatedElementUtils.hasAnnotation(method, ModelAttribute.class));
 * 			public static final MethodFilter INIT_BINDER_METHODS = method ->
 * 			AnnotatedElementUtils.hasAnnotation(method, InitBinder.class);
 */

/**
 * @ControllerAdvice 注解用于定义全局的异常处理和全局数据绑定规则。它可以被用于一个或多个类上，这些类会被自动扫描并应用于整个应用程序中的所有 @Controller
 * @ControllerAdvice 是Spring 3.2提供的新注解，可以对Controller中使用到@RequestMapping注解的方法做逻辑处理。
 * 作用：
 * 1.异常处理：
 * @ControllerAdvice 可以用于定义全局异常处理器，它允许你捕获应用程序中抛出的异常，并对它们进行统一的处理。
 * 你可以在@ControllerAdvice注解的类中定义多个@ExceptionHandler方法，每个方法处理不同的异常类型。
 * 当应用程序中抛出异常时，Spring Boot会根据异常类型调用相应的处理方法，并返回处理结果。
 */

@Component
@RestControllerAdvice
public class MvcTest3Controller implements RequestBodyAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(MvcTest3Controller.class);


    @ExceptionHandler(RuntimeException.class)
    public RestResponse handleException(Exception e) {
        LOGGER.error(e.toString());
        return RestResponse.error("程序出错了~", e.getMessage());
    }

    @ModelAttribute
    public void test(Model model) {
        System.out.println("ModelAttribute.test");
        model.addAttribute("secret_code", 5250);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return null;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}
