package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.Mail;
import org.ua.deth.javamailsender.service.MailService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class MailController {

    private final MailService service;

    @Autowired
    public MailController(MailService service) {
        this.service = service;
    }

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
    public ModelAndView addTemplate(@ModelAttribute("mailTemplate") Mail mail, @RequestParam MultipartFile file) {
        System.out.println(file.getName());
        service.saveMailTemplate(mail);
        return new ModelAndView("redirect:/mail/mail-templates");
    }

    @RequestMapping(value = "/mail/upload", method = RequestMethod.POST)
    public ModelAndView uploadAttachment(@ModelAttribute("mailTemplate") Mail mail, @RequestParam MultipartFile file) {
        try {
            mail.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getOriginalFilename());
        ModelAndView modelAndView = new ModelAndView("redirect:/mail/add-mail-template");
        modelAndView.addObject("mailTemplate", mail);
        service.saveMailTemplate(mail);
        return modelAndView;
    }

}
