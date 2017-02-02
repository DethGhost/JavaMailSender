package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.config.MailConfig;
import org.ua.deth.javamailsender.entity.Mail;
import org.ua.deth.javamailsender.entity.Subscriber;
import org.ua.deth.javamailsender.service.MailService;
import org.ua.deth.javamailsender.service.MailSettingService;
import org.ua.deth.javamailsender.service.SubscriberGroupService;
import org.ua.deth.javamailsender.service.SubscriberService;

import java.util.List;

@Controller


/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public class SendMailController {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberGroupService subscriberGroupService;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailSettingService mailSettingService;

    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/mail/send-mails", method = RequestMethod.GET)
    public ModelAndView getSendMail() {
        ModelAndView modelAndView = new ModelAndView("/mail/send-mails");
        modelAndView.addObject("subscriberGroup", subscriberGroupService.getAllGroup());
        modelAndView.addObject("mailTemplate", mailService.getAllMailTemplates());
        return modelAndView;
    }

    @RequestMapping(value = "/mail/send", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam("template") long mailTemplateId, @RequestParam("subscriberList") long subscriberListId) {
        MailConfig mailConfig = new MailConfig(mailSettingService.getMailSetting());
        javaMailSender = mailConfig.getJavaMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Mail mail = mailService.getOneMailTemplate(mailTemplateId);
        List<Subscriber> subscribers = subscriberService.getByGroup(subscriberListId);
        String[] subscribersList = new String[subscribers.size()];
        for (int i = 0; i < subscribers.size(); i++) {
            subscribersList[i] = subscribers.get(i).getFullName() + "<" + subscribers.get(i).getEmail() + ">";
        }
        mailMessage.setText(mail.getText());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setBcc(subscribersList);
        javaMailSender.send(mailMessage);

        return new ModelAndView("/mail/result");
    }

}
