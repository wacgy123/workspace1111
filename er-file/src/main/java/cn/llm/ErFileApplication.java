package cn.llm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ErFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErFileApplication.class, args);
    }

}
