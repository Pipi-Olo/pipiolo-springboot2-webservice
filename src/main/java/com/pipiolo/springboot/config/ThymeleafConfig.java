package com.pipiolo.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver templateResolver
    ) {
        templateResolver.setUseDecoupledLogic(Boolean.TRUE);
        return templateResolver;
    }
}
