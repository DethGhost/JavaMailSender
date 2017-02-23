package org.ua.deth.javamailsender.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.ua.deth.javamailsender.config.DirectoryConfig;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("org.ua.deth.javamailsender")
public class JavaMailSenderApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JavaMailSenderApplication.class, args);
        OpenUrlOnStartUp.openUrl();
        new DirectoryConfig().getHome();
    }

}
