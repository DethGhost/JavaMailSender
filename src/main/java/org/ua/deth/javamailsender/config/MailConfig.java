package org.ua.deth.javamailsender.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.ua.deth.javamailsender.entity.MailSetting;

import java.util.Properties;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 * <p>
 * I could not make it through the start of Spring Booth, and that use of such a terrible approach
 */
public class MailConfig {

    private static MailConfig ourInstance = new MailConfig();
    private String host;
    private int port;
    private String username;
    private String password;
    private String auth;
    private String starttls;

    private MailConfig() {

    }

    public static MailConfig getInstance() {
        return ourInstance;
    }

    // Setup setting for our JavaMailSender
    public void setSetting(MailSetting setting) {
        this.host = setting.getHost();
        this.port = setting.getPort();
        this.username = setting.getUserName();
        this.password = setting.getPassword();
        this.auth = Boolean.toString(setting.getSmtpAuth());
        this.starttls = Boolean.toString(setting.getStarttls());
    }

    // Place setting to JavaMailSender
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(username);
        javaMailSender.setHost(host);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    // Setup properties to JavaMailSender
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.mime.charset", "UTF-8");
        properties.setProperty("mail.smtp.starttls.enable", starttls);
        return properties;
    }
}
