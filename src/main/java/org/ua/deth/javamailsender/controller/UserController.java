package org.ua.deth.javamailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.ua.deth.javamailsender.entity.User;
import org.ua.deth.javamailsender.entity.UserGroup;
import org.ua.deth.javamailsender.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users(HttpSession session, Map<String, Object> map) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("raccoon", "Raccoonn!!!");
        map.put("userList", service.getAllUsers());
        modelAndView.addObject("userList", service.getAllUsers());
        session.setAttribute("userList", service.getAllUsers());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(value = "add-user", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView("add-user");
        modelAndView.addObject("userForm", new User());
        modelAndView.addObject("groupList", UserGroup.values());
        return modelAndView;
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("userForm") @Validated User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("add-user");
        } else if (service.isExist(user.getLogin())) {
            modelAndView.setViewName("add-user");
            modelAndView.addObject("loginExist", "Login already used. Choose another login name.");
        } else {
            service.save(user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}
