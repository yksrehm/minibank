package com.mherscode.minibank;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MinibankApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return buildApplication(application);
    }

    private static SpringApplicationBuilder buildApplication(SpringApplicationBuilder application) {
        return application.sources(MinibankApplication.class);
    }

    public static void main(String[] args) {
        buildApplication(new SpringApplicationBuilder()).run(args);
    }

}
