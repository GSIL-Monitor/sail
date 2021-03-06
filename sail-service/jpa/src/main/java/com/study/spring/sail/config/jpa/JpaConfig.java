package com.study.spring.sail.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JPA 配置类 事务配置
 *
 * @author 韩炜
 * @date 2019-01-17 16:27
 */
@Configuration
@EnableJpaRepositories("com.study.spring.sail.modules.**.jpaImpl.dao")
@EntityScan("com.study.spring.sail.modules.**.jpaImpl.entity")
@EnableTransactionManagement
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        return transactionManager;
    }
}
