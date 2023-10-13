package com.wz.example.template.mvc;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Component
@ControllerAdvice
public class MvcTest3Controller implements RequestBodyAdvice {

    @ModelAttribute
    public Object test() {
        System.out.println("test");
        return null;
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
