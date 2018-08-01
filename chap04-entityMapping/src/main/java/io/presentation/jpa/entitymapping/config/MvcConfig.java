package io.presentation.jpa.entitymapping.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created By Minhyuk Yoon on 2018. 7. 31.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"${base.package}.${base.service.package}"})
public class MvcConfig extends WebMvcConfigurerAdapter {

}