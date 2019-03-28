package com.epam.addressbook.leasing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.TimeZone;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@EnableEurekaClient
@ComponentScan({"com.epam.addressbook.leasing", "com.epam.addressbook.restsupport"})
public class LeasingApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(LeasingApplication.class, args);
    }

    @Bean
    HousingClient housingClient(RestTemplate restTemplate, @Value("${registration.server.endpoint}") String registrationEndpoint) {
        return new HousingClient(restTemplate, registrationEndpoint);
    }
}