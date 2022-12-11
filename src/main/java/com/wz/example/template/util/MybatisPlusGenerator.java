package com.wz.example.template.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerator {

    private static String url = "jdbc:mysql://101.43.189.54:52/common?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&useSSL=false";
    private static String username = "root";
    private static String password = "wz201405001148";

    private static void generate() {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Zz_Wang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wz.example.template") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .controller("controller")
                            .service("service")
                            .serviceImpl("service.impl")
                            .entity("entity")
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper")); // 设置mapperXml生成路径
                })
//                .templateConfig(builder -> {
//                    builder.disable(TemplateType.ENTITY)
//                            .entity("/templates/entity.java")
//                            .service("/templates/service.java")
//                            .serviceImpl("/templates/serviceImpl.java")
//                            .mapper("/templates/mapper.java")
//                            .xml("/templates/mapper.xml")
//                            .controller("/templates/controller.java")
//                            .build();
//                })
                .strategyConfig(builder -> {
                    builder.addInclude("cache_interface_config"); // 设置需要生成的表名
                    builder.entityBuilder().fileOverride();
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    public static void main(String[] args) {
        generate();
    }
}
