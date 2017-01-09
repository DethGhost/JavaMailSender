package org.ua.deth.javamailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.repository.MailSettingRepository;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class MailSettingServiceImpl implements MailSettingService {

    @Autowired
    MailSettingRepository repository;

    @Override
    public ModelAndView getSetting() {
        ModelAndView modelAndView = new ModelAndView();
        MailSetting setting = new MailSetting();
        modelAndView.addObject("setting", setting);
        modelAndView.setViewName("/setting");
        return modelAndView;
    }

    @Override
    public boolean saveSetting(MailSetting setting) {
        try {
            repository.saveAndFlush(setting);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
