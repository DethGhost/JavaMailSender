package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.Mail;
import org.ua.deth.javamailsender.service.MailService;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class MailController {

    @Autowired
    private MailService service;

    @RequestMapping(value = "/mail/mail-templates", method = RequestMethod.GET)
    public ModelAndView mailTemplates() {
        ModelAndView modelAndView = new ModelAndView("/mail/mail-templates");
        modelAndView.addObject("mailTemplateList", service.getAllMailTemplates());
        return modelAndView;
    }

    @RequestMapping(value = "/mail/add-mail-template", method = RequestMethod.GET)
    public ModelAndView addMailTemplate() {
        ModelAndView modelAndView = new ModelAndView("/mail/add-mail-template");
        modelAndView.addObject("mailTemplate", new Mail());
        return modelAndView;
    }

    @RequestMapping(value = "/mail/add-template", method = RequestMethod.POST)
    public ModelAndView addTemplate(@ModelAttribute("mailTemplate") Mail mail) {
        service.saveMailTemplate(mail);
        return new ModelAndView("redirect:/mail/mail-templates");
    }



}
