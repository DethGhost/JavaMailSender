package org.ua.deth.javamailsender.service;

import org.ua.deth.javamailsender.entity.Mail;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */

public interface MailService {

    void saveMailTemplate(Mail mail);

    void deleteMailTemplate(long id);

    List<Mail> getAllMailTemplates();

    Mail getOneMailTemplate(long id);

}
