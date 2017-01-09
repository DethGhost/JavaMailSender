package org.ua.deth.javamailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.repository.MailSettingRepository;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class MailSettingServiceImpl implements MailSettingService {

    @Autowired
    private MailSettingRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean saveSetting(MailSetting setting) {
        setting.setPassword(passwordEncoder.encode(setting.getPassword()));
        try {
            repository.save(setting);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public MailSettingRepository getRepository() {
        return repository;
    }


}
