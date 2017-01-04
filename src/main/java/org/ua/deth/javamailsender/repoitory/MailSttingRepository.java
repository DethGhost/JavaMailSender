package org.ua.deth.javamailsender.repoitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.deth.javamailsender.entity.MailSetting;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface MailSttingRepository extends JpaRepository<MailSetting, Long> {
}
