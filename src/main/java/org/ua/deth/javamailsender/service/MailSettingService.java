package org.ua.deth.javamailsender.service;

import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.repository.MailSettingRepository;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface MailSettingService {

    MailSettingRepository getRepository();

    boolean saveSetting(MailSetting setting);
}
