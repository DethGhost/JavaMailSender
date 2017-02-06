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
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.entity.Subscriber;
import org.ua.deth.javamailsender.service.MailService;
import org.ua.deth.javamailsender.service.MailSettingService;
import org.ua.deth.javamailsender.service.SubscriberGroupService;
import org.ua.deth.javamailsender.service.SubscriberService;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SendMailController {

    private final SubscriberService subscriberService;

    private final SubscriberGroupService subscriberGroupService;

    private final MailService mailService;

    private final MailSettingService mailSettingService;

    private MailConfig mailConfig = MailConfig.getInstance();

    @Autowired
    public SendMailController(SubscriberService subscriberService, SubscriberGroupService subscriberGroupService, MailService mailService, MailSettingService mailSettingService) {
        this.subscriberService = subscriberService;
        this.subscriberGroupService = subscriberGroupService;
        this.mailService = mailService;
        this.mailSettingService = mailSettingService;
    }


    @RequestMapping(value = "/mail/send-mails", method = RequestMethod.GET)
    public ModelAndView getSendMail() {
        ModelAndView modelAndView = new ModelAndView("/mail/send-mails");
        modelAndView.addObject("subscriberGroup", subscriberGroupService.getAllGroup());
        modelAndView.addObject("mailTemplate", mailService.getAllMailTemplates());
        return modelAndView;
    }

    @RequestMapping(value = "/mail/send", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam("template") String mailTemplateId, @RequestParam("subscriberList") String subscriberListId) {
        MailSetting setting = mailSettingService.getMailSetting();
        mailConfig.setSetting(setting);
        JavaMailSender javaMailSender = mailConfig.getJavaMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Mail mail = mailService.getOneMailTemplate(Long.parseLong(mailTemplateId));
        mailMessage.setFrom(setting.getFromName() + "<" + setting.getFrom() + ">");
        mailMessage.setText(mail.getText());
        mailMessage.setSubject(mail.getSubject());
        List<Subscriber> subscribers = subscriberService.getByGroup(Long.parseLong(subscriberListId));
        for (Subscriber subscriber : subscribers) {
            try {
                mailMessage.setTo(new InternetAddress(subscriber.getEmail(), subscriber.getFullName(), "UTF-8").toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            javaMailSender.send(mailMessage);
        }


        return new ModelAndView("/mail/result");
    }

}
