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

import javax.servlet.http.HttpSession;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SettingController {

    @Autowired
    private MailSettingService service;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView getSetting(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mailSetting", service.getRepository().findOne(1L));
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
