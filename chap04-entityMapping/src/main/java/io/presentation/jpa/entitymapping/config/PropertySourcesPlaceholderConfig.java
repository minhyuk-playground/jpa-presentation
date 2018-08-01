package io.presentation.jpa.entitymapping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created By Minhyuk Yoon on 2018. 7. 31.
 */
@Configuration
@PropertySource(value = "classpath:/config/${env:local}/system.properties")
public class PropertySourcesPlaceholderConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
