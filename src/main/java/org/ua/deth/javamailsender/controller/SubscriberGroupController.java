package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.SubscriberGroup;
import org.ua.deth.javamailsender.service.SubscriberGroupService;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SubscriberGroupController {

    @Autowired
    private SubscriberGroupService groupService;

    @RequestMapping(value = "subscribers", method = RequestMethod.GET)
    public ModelAndView subscribers() {
        return new ModelAndView("redirect:/subscribers/subscriber-group");
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
    public ModelAndView editGroup(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("/setting/edit-subscriber-group");
        modelAndView.addObject("groupEdit", groupService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/setting/delete-group", method = RequestMethod.POST)
    public ModelAndView editGroup(@RequestParam("id") long id, @RequestParam("whatToDo") boolean delete) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/setting/subscriber-group-list");
        if (delete) {
            groupService.deleteById(id);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/setting/do-edit-group", method = RequestMethod.POST)
    public ModelAndView doEdit(@RequestParam("group") String group, @RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/setting/subscriber-group-list");
        SubscriberGroup subscriberGroup = groupService.findById(id);

        subscriberGroup.setGroupName(group);
        groupService.save(subscriberGroup);
        return modelAndView;
    }

}
