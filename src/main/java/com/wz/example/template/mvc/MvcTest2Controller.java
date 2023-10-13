package com.wz.example.template.mvc;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BeanNameUrlHandlerMapping处理的请求，之后会执行initApplicationContext()方法
 * 识别Handler的方式简单粗暴：
 * 	protected String[] determineUrlsForHandler(String beanName) {
 * 		List<String> urls = new ArrayList<>();
 * 		if (beanName.startsWith("/")) {
 * 			urls.add(beanName);
 *                }
 * 		String[] aliases = obtainApplicationContext().getAliases(beanName);
 * 		for (String alias : aliases) {
 * 			if (alias.startsWith("/")) {
 * 				urls.add(alias);
 *            }
 *        }
 * 		return StringUtils.toStringArray(urls);
 * 	}
 * 	把所有的Bean找出来，beanName以"/"开头的都算Handler
 * 	至于要执行什么方法：
 * 	① 可实现Controller接口，并重写handleRequest方法
 * 	② 可实现HttpRequestHandler接口，并重写handleRequest方法
 *
 */
@Component("/mvc")
public class MvcTest2Controller implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("test2");
        return null;
    }

    class MvcTest implements HttpRequestHandler{

        @Override
        public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }
}
