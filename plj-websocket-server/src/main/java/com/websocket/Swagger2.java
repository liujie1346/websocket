package com.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 庞留杰
 * @create 2018-05-24 14:09
 * @descriptions <p>@Configuration注解，让Spring来加载该类配置。
 *                  再通过@EnableSwagger2注解来启用Swagger2。</p >
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ergu.vctwebsocket.controller"))//Swagger会扫描该包下所有Controller定义的API,并产生文档内容
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bibao-APIs")
                .description("介绍币酷行情API，为用户提供调用方法及接口说明")
                .termsOfServiceUrl("https://www.bao.top/")
                .contact("河南尔谷科技有限公司")
                .version("1.0")
                .build();
    }
}
