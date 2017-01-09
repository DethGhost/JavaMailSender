package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.service.MailSettingService;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SettingController {

    @Autowired
    private MailSettingService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView getSetting() {
        ModelAndView modelAndView = new ModelAndView();
        if (service.getRepository().findOne(1L) != null) {
            modelAndView.addObject("mailSetting", service.getRepository().findOne(1L));
        } else {
            modelAndView.addObject("mailSetting", new MailSetting());
        }
        modelAndView.setViewName("setting");
        return modelAndView;
    }

    @RequestMapping(value = "/saveSettings", method = RequestMethod.POST)
    public ModelAndView saveSetting(@ModelAttribute("mailSetting") @Validated MailSetting setting, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("setting");
        } else if (service.saveSetting(setting)) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("setting");
            modelAndView.addObject("error", "some error going out");
        }
        return modelAndView;
    }

}
