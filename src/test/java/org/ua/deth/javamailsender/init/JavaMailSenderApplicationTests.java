package org.ua.deth.javamailsender.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.ua.deth.javamailsender.config.MailConfig;
import org.ua.deth.javamailsender.entity.Mail;
import org.ua.deth.javamailsender.entity.MailSetting;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaMailSenderApplicationTests.class)
public class JavaMailSenderApplicationTests {

    @Test
    public void contextLoads() {
    }



    @Test
    public void sendEmail() {
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
        Mail mailTest = new Mail();
        mailTest.setSubject("Testing Mail");
        mailTest.setText("Testing mail from my mail Sender");

        MailConfig mailConfig = new MailConfig(
                setting.getHost(),
                setting.getPort(),
                setting.getFrom(),
                setting.getUserName(),
                setting.getPassword(),
                setting.getSmtpAuth(),
                setting.getStarttls());
        MailSender mailSender = mailConfig.getJavaMailSender();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("\""+setting.getFromName()+"\""+"<"+setting.getFrom()+">");
        simpleMailMessage.setText(mailTest.getText());
        simpleMailMessage.setSubject(mailTest.getSubject());
        simpleMailMessage.setTo("eugenkhudoliiv@ukr.net");
        mailSender.send(simpleMailMessage);
    }

}
