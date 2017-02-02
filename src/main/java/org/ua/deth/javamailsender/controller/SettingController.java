package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/setting/settings", method = RequestMethod.GET)
    public ModelAndView getSetting() {
        ModelAndView modelAndView = new ModelAndView();
        if (service.getMailSetting() != null) {
            modelAndView.addObject("mailSetting", service.getMailSetting());
        } else {
            modelAndView.addObject("mailSetting", new MailSetting());
        }
        modelAndView.setViewName("/setting/settings");
        return modelAndView;
    }

    @RequestMapping(value = "/setting/saveSettings", method = RequestMethod.POST)
    public ModelAndView saveSetting(@ModelAttribute("mailSetting") @Validated MailSetting setting, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("/setting/settings");
        } else if (service.saveSetting(setting)) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("/setting/settings");
            modelAndView.addObject("error", "some error going out");
        }
        return modelAndView;
    }

}
