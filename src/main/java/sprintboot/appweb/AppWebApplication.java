package sprintboot.appweb;

import sprintboot.appweb.config.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CustomProperties.class)
public class AppWebApplication implements CommandLineRunner {

    @Autowired
    private CustomProperties customProperties;

    public static void main(String[] args) {

        SpringApplication.run(AppWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(customProperties.getApiUrl());
    }
}
