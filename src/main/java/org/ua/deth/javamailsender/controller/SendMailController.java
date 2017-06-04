package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    public ModelAndView send(@RequestParam("template") String mailTemplateId, @RequestParam("subscriberList") String subscriberListId, @RequestParam("oneToOne") boolean oneToOne) {
        ModelAndView modelAndView = new ModelAndView("/mail/result");
        MailSetting setting = mailSettingService.getMailSetting();
        mailConfig.setSetting(setting);
        List<Subscriber> subscribers = subscriberService.getByGroup(Long.parseLong(subscriberListId));
        JavaMailSender javaMailSender = mailConfig.getJavaMailSender();
        Mail mail = mailService.getOneMailTemplate(Long.parseLong(mailTemplateId));
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        if (oneToOne) {
            sendOneToOne(subscribers, mailMessage, mail, setting, javaMailSender);
        } else {
            sendOneToAll(subscribers, mailMessage, mail, setting, javaMailSender);
        }
        return modelAndView;
    }

    // This method send one email to all subscribers and hide their email addresses from each other
    private List sendOneToAll(List<Subscriber> subscribers, MimeMessage mailMessage, Mail mail, MailSetting setting, JavaMailSender javaMailSender) {

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            mailMessage.setContent(mail.getText(), "text/html");
            helper.setSubject(mail.getSubject());
            helper.setFrom(new InternetAddress(setting.getFrom(), setting.getFromName(), "UTF-8").toString());
            InternetAddress[] addresses = new InternetAddress[subscribers.size()];
            for (int i = 0; i < addresses.length; i++) {
                addresses[i] = new InternetAddress( subscribers.get(i).getEmail(), subscribers.get(i).getFullName(), "UTF-8");
            }
            helper.setBcc(addresses);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mailMessage);
        return subscribers;
    }

    // This method sends personal email to each subscriber
    private List sendOneToOne(List<Subscriber> subscribers, MimeMessage mailMessage, Mail mail, MailSetting setting, JavaMailSender javaMailSender) {
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mailMessage, true);
            mailMessage.setContent(mail.getText(), "text/html");
            helper.setSubject(mail.getSubject());
            helper.setFrom(new InternetAddress(setting.getFrom(), setting.getFromName(), "UTF-8").toString());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (Subscriber subscriber : subscribers) {
            try {
                assert helper != null;
                helper.setTo(new InternetAddress(subscriber.getEmail(), subscriber.getFullName(), "UTF-8").toString());
            } catch (UnsupportedEncodingException | MessagingException e) {
                e.printStackTrace();
            }

            javaMailSender.send(mailMessage);
            System.out.println("Email was send to: " + subscriber.getFullName() + " " + subscriber.getEmail());
        }
        return subscribers;
    }

}
