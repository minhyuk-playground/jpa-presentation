package io.presentation.jpa.entitymapping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created By Minhyuk Yoon on 2018. 7. 31.
 */
@Configuration
@Import(value = {
        PropertySourcesPlaceholderConfig.class,
        JPAConfig.class
})
public class ServiceConfig extends MvcConfig{

}
