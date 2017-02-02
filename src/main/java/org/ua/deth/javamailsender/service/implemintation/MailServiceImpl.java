package org.ua.deth.javamailsender.service.implemintation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.Mail;
import org.ua.deth.javamailsender.repository.MailRepository;
import org.ua.deth.javamailsender.service.MailService;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class MailServiceImpl implements MailService {

    private final MailRepository repository;

    @Autowired
    public MailServiceImpl(MailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveMailTemplate(Mail mail) {
        return repository.save(mail);
    }

    @Override
    public void deleteMailTemplate(long id) {
        repository.delete(id);
    }

    @Override
    public List<Mail> getAllMailTemplates() {
        return repository.findAll();
    }

    @Override
    public Mail getOneMailTemplate(long id) {
        return repository.findOne(id);
    }
}
