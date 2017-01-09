package org.ua.deth.javamailsender.service;

import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.MailSetting;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface MailSettingService {
    ModelAndView getSetting();

    boolean saveSetting(MailSetting setting);
}
