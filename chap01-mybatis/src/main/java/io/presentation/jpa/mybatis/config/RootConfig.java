package io.presentation.jpa.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
@Configuration
@MapperScan(basePackages = "io.presentation.jpa.mybatis.mapper")
public class RootConfig {

    @Bean
    public DataSource dataSource() {

        String driverClassName = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/TestDB?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {

        String typeAliasesPackage = "io.presentation.jpa.mybatis.domain";
        String mapperLocation = "classpath:/mybatis/mappers/*Mapper.xml";

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));

        return sqlSessionFactoryBean.getObject();
    }
}
