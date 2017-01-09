package org.ua.deth.javamailsender.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Entity(name = "mail_setting")
public class MailSetting {

    @Id
    private long id = 1L;

    @Column
    private String host;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    @Column
    private int port;

    @Column(name = "from_email")
    private String from;

    @Column(name = "from_name")
    private String fromName;

    @Column(name = "smtp_auth")
    private boolean isSmtpAuth;

    @Column(name = "isStarttls")
    private boolean isStarttls;

    public MailSetting() {
    }

    public MailSetting(String host, String userName, String password, int port, String from, String fromName, boolean smtpAuth, boolean starttls) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.port = port;
        this.from = from;
        this.fromName = fromName;
        this.isSmtpAuth = smtpAuth;
        this.isStarttls = starttls;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public boolean getSmtpAuth() {
        return isSmtpAuth;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        this.isSmtpAuth = smtpAuth;
    }

    public boolean getStarttls() {
        return isStarttls;
    }

    public void setStarttls(boolean starttls) {
        this.isStarttls = starttls;
    }
}
