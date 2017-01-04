package org.ua.deth.javamailsender.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 *
 * I can`t do spring-boot autowired this config, use that monster...
 */

public class MailConfig {

    private String host;
    private int port;
    private String from;
    private String username;
    private String password;
    private String auth;
    private String starttls;

    public static MailConfig ourInstance = new MailConfig();

    public static MailConfig getInstance() {
        return ourInstance;
    }

    public JavaMailSenderImpl getJavaMailSender(){
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

    public MailConfig() {
    }

    public MailConfig(String host, int port, String from, String username, String password, String auth, String starttls) {
        this.host = host;
        this.port = port;
        this.from = from;
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.starttls = starttls;
    }
}
