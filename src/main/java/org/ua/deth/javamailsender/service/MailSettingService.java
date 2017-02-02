package org.ua.deth.javamailsender.service;

import org.ua.deth.javamailsender.entity.MailSetting;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface MailSettingService {

    MailSetting getMailSetting();

    boolean saveSetting(MailSetting setting);

    
}
