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
import org.ua.deth.javamailsender.entity.MailSetting;
import org.ua.deth.javamailsender.entity.SubscriberGroup;
import org.ua.deth.javamailsender.service.MailSettingService;
import org.ua.deth.javamailsender.service.SubscriberGroupService;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SettingController {

    @Autowired
    private MailSettingService service;
    @Autowired
    private SubscriberGroupService groupService;

    @RequestMapping(value = "/setting/settings", method = RequestMethod.GET)
    public ModelAndView getSetting() {
        ModelAndView modelAndView = new ModelAndView();
        if (service.getRepository().findOne(1L) != null) {
            modelAndView.addObject("mailSetting", service.getRepository().findOne(1L));
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

    @RequestMapping(value = "/setting/add-subscriber-group", method = RequestMethod.GET)
    public ModelAndView addSubscriberGroup() {
        ModelAndView modelAndView = new ModelAndView("setting/add-subscriber-group");
        modelAndView.addObject("subscriberGroup", new SubscriberGroup());
        return modelAndView;
    }

    @RequestMapping(value = "/setting/save-group", method = RequestMethod.POST)
    public ModelAndView saveSubscriberGroup(@ModelAttribute("subscriberGroup") SubscriberGroup subscriberGroup) {
        groupService.save(subscriberGroup);
        return new ModelAndView("redirect:/setting/subscriber-group-list");
    }

    @RequestMapping(value = "/setting/subscriber-group-list", method = RequestMethod.GET)
    public ModelAndView subscriberGroup() {
        ModelAndView modelAndView = new ModelAndView("/setting/subscriber-group-list");
        modelAndView.addObject("groupList", groupService.getAllGroup());
        return modelAndView;
    }

    @RequestMapping(value = "/setting/edit-group", method = RequestMethod.GET)
    public ModelAndView editGroup(@RequestParam("id") long id,@RequestParam("delete") long delete) {
        ModelAndView modelAndView = new ModelAndView("/setting/edit-subscriber-group");
        if(delete != 0){
            groupService.deleteById(id);//!TODO check delete method
        }
        modelAndView.addObject("groupEdit", groupService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/setting/do-edit-group", method = RequestMethod.POST)
    public ModelAndView doEdit(@RequestParam("group")String group,@RequestParam("id")long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/setting/subscriber-group-list");
        SubscriberGroup subscriberGroup = groupService.findById(id);
        subscriberGroup.setGroupName(group);
        groupService.save(subscriberGroup);
        return modelAndView;
    }

}
