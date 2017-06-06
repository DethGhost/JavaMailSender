package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.config.DirectoryConfig;
import org.ua.deth.javamailsender.entity.Mail;
import org.ua.deth.javamailsender.service.MailService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class MailController {

    private final MailService service;
    private String dir = System.getProperty("os.name").toLowerCase().contains("win") ? DirectoryConfig.getInstance().getDirToCreate() + "upload\\" : DirectoryConfig.getInstance().getDirToCreate() + "upload/";

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
    public ModelAndView addTemplate(@RequestParam String subject, @RequestParam String text, @RequestParam MultipartFile file) {
        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setText(text);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(dir + file.getOriginalFilename());
                mail.setFile(path.toString());

                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        service.saveMailTemplate(mail);
        return new ModelAndView("redirect:/mail/mail-templates");
    }

    @RequestMapping(value = "/mail/upload", method = RequestMethod.POST)
    public ModelAndView uploadAttachment(@ModelAttribute("mailTemplate") Mail mail, @RequestParam MultipartFile file) {
        mail.setFile(null);
        System.out.println(file.getOriginalFilename());
        ModelAndView modelAndView = new ModelAndView("redirect:/mail/add-mail-template");
        modelAndView.addObject("mailTemplate", mail);
        service.saveMailTemplate(mail);
        return modelAndView;
    }

}
