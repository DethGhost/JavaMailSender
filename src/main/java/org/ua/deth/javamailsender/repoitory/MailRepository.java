package org.ua.deth.javamailsender.repoitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.deth.javamailsender.entity.Mail;


/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface MailRepository extends JpaRepository<Mail, Long> {
}
