package org.ua.deth.javamailsender.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.repository.MailSettingRepository;

@SpringBootApplication
@ComponentScan("org.ua.deth.javamailsender")
public class JavaMailSenderApplication {

    @Autowired
    private static MailSettingRepository mailSettingRepository;

    public static void main(String[] args) {
        SpringApplication.run(JavaMailSenderApplication.class, "--debug");
        save();
    }

   static void save(){
        MailSetting setting = new MailSetting(
                "mail.ukraine.com.ua",
                "eugen@karpkoi.com.ua",
                "test",
                2525,
                "eugen@karpkoi.com.ua",
                "Eugen Khudoliiv",
                "UTF-8",
                "true",
                "false");
        mailSettingRepository.save(setting);
    }

}
