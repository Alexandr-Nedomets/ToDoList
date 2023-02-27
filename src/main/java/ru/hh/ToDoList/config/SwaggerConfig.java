package ru.hh.ToDoList.config;

import com.google.common.base.Predicates;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Base API")
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(any())
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .build()
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .tags(new Tag("task-rest-controller", "API для взаимодействия с задачами"))
        .tags(new Tag("task-status-rest-controller", "API для взаимодействия со статусами задач"));
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "TO DO LIST API",
        "Актуальное описание API для взаимодействия",
        "1.0.O",
        "",
        new Contact("Manager", "list.ru", "to.do.list@yandex.ru"),
        "Test License",
        "http://localhost:8080/",
        Collections.emptyList());
  }

}
