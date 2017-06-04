package org.ua.deth.javamailsender.entity;

import javax.persistence.*;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 *
 * This class create a mail message and save it to DB. Than you can use it in your setting to sending email
 *
 */

@Entity
public class Mail {

    @Id
    @SequenceGenerator(name = "mail_id_seq", sequenceName = "mail_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_id_seq")
    @Column(name = "mail_id")
    private long mailId;

    @Column
    private String subject;

    @Column(columnDefinition="TEXT")
    private String text;

    
    private byte[] file;

    public Mail() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getMailId() {
        return mailId;
    }

    public void setMailId(long mailId) {
        this.mailId = mailId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
