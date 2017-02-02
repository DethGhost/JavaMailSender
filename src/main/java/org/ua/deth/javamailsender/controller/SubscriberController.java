package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.Subscriber;
import org.ua.deth.javamailsender.service.SubscriberGroupService;
import org.ua.deth.javamailsender.service.SubscriberService;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SubscriberController {

    @Autowired
    private SubscriberService service;

    @Autowired
    private SubscriberGroupService groupService;

    @RequestMapping(value = "/subscribers", method = RequestMethod.GET)
    public ModelAndView getSubscribers() {
        ModelAndView modelAndView = new ModelAndView("/subscribers/subscriber-list");
        modelAndView.addObject("subscribers", service.getAllSubscribers());
        return modelAndView;
    }

    @RequestMapping(value = "/subscribers/subscriber-list", method = RequestMethod.GET)
    public ModelAndView getSubscribersList() {
        ModelAndView modelAndView = new ModelAndView("/subscribers/subscriber-list");
        modelAndView.addObject("subscribers", service.getAllSubscribers());
        return modelAndView;
    }

    @RequestMapping(value = "/subscribers/add-subscriber", method = RequestMethod.GET)
    public ModelAndView addSubscriber() {
        ModelAndView modelAndView = new ModelAndView("/subscribers/add-subscriber");
        modelAndView.addObject("groupList", groupService.getAllGroup());
        modelAndView.addObject("subscriberForm", new Subscriber());
        return modelAndView;
    }

    @RequestMapping(value = "/subscribers/add-subscriber", method = RequestMethod.POST)
    public ModelAndView saveSubscriber(@ModelAttribute("subscriberForm") @Validated Subscriber subscriber, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("/subscribers/add-subscriber");
        try {
            service.save(subscriber);
            modelAndView.setViewName("redirect:/subscribers/subscriber-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("subscriberForm", new Subscriber());
        return modelAndView;
    }

    @RequestMapping(value = "/subscribers/save-subscriber", method = RequestMethod.POST)
    public ModelAndView saveSubscriber(@ModelAttribute("subscriberForm") Subscriber subscriber) {
        service.save(subscriber);
        return new ModelAndView("redirect:/subscribers");
    }

    @RequestMapping(value = "/subscribers/edit-subscriber", method = RequestMethod.GET)
    public ModelAndView editSubscriber(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("/subscribers/edit-subscriber");
        modelAndView.addObject("subscriberForm", service.getSubscriberById(id));
        return modelAndView;
    }
}
