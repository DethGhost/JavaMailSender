package org.ua.deth.javamailsender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class SubscriberGroupController {

    @RequestMapping(value = "subscribers", method = RequestMethod.GET)
    public ModelAndView subscribers() {
        return new ModelAndView("redirect:/subscribers/subscriber-group");
    }



}
