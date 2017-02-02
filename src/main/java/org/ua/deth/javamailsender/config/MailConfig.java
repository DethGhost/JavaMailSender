package org.ua.deth.javamailsender.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.ua.deth.javamailsender.entity.MailSetting;

import java.util.Properties;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 * <p>
 * I can`t do spring-boot autowired this config, use that monster...
 */

public class MailConfig {

    public static MailConfig ourInstance = new MailConfig();
    private String host;
    private int port;
    private String from;
    private String username;
    private String password;
    private String auth;
    private String starttls;

    public MailConfig() {

    }

    public MailConfig(MailSetting setting) {
        this.host = setting.getHost();
        this.port = setting.getPort();
        this.from = setting.getFrom();
        this.username = setting.getUserName();
        this.password = setting.getPassword();
        this.auth = Boolean.toString(setting.getSmtpAuth());
        this.starttls = Boolean.toString(setting.getStarttls());
    }

    public static MailConfig getInstance() {
        return ourInstance;
    }

    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(username);
        javaMailSender.setHost(host);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.starttls.enable", starttls);
        return properties;
    }
}
