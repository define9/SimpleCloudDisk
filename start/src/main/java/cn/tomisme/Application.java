package cn.tomisme;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = {"cn.tomisme","com.alibaba.cola"})
@MapperScan("cn.tomisme.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
