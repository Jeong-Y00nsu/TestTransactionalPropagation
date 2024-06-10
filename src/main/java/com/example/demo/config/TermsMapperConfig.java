package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.mapper.terms", sqlSessionFactoryRef = "termsSqlSessionFactory")
public class TermsMapperConfig {

    @Bean(name = "termsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.terms")
    public DataSource termsDataSource(){ return DataSourceBuilder.create().build();}

    @Bean(name="termsSqlSessionFactory")
    public SqlSessionFactory termsSqlSessionFactory(@Qualifier("termsDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/terms/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name="termsSqlSessionTemplate")
    public SqlSessionTemplate termsSqlSessionTemplate(@Qualifier("termsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
