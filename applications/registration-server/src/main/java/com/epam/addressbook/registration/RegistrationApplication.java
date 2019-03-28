package com.epam.addressbook.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@EnableEurekaClient
@ComponentScan({"com.epam.addressbook.account", "com.epam.addressbook.restsupport", "com.epam.addressbook.housing", "com.epam.addressbook.person"})
public class RegistrationApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(RegistrationApplication.class, args);
    }
}