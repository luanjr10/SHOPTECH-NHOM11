package com.shoptech.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Dự án dùng cả JPA (MySQL) lẫn MongoDB: chỉ định rõ repository nào thuộc store nào
 * theo interface cha, thay vì để Spring Data đoán (và log cảnh báo cho từng repository).
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.shoptech.modules",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JpaRepository.class))
@EnableMongoRepositories(
        basePackages = "com.shoptech.modules",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MongoRepository.class))
public class RepositoryConfig {
}
