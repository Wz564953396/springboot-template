package com.wz.example.template.controller;

import com.wz.example.template.handler.VideoHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoHttpRequestHandler videoHttpRequestHandler;

    private final String filePath = "C:\\Users\\Zz_Wang\\Desktop\\32842aec3ed7e8e20c59225fc16a2234.mp4";

    @GetMapping("player")
    public void getFromStatic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            String mimeType = Files.probeContentType(path);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(VideoHttpRequestHandler.ATTR_FILE, path);
            videoHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

}
