package org.ua.deth.javamailsender.service.implemintation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.repository.MailSettingRepository;
import org.ua.deth.javamailsender.service.MailSettingService;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class MailSettingServiceImpl implements MailSettingService {

    @Autowired
    private MailSettingRepository repository;

    @Override
    public MailSetting getMailSetting() {
        return null;
    }

    @Override
    public boolean saveSetting(MailSetting setting) {
        try {
            repository.save(setting);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
