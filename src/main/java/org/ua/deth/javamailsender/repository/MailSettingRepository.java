package org.ua.deth.javamailsender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ua.deth.javamailsender.entity.MailSetting;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Repository
public interface MailSettingRepository extends CrudRepository<MailSetting, Long> {
}
