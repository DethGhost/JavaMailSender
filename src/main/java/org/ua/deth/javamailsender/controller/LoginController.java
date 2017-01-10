package org.ua.deth.javamailsender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest httpRequestHandler) {
        if (httpRequestHandler.getRemoteUser() == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("redirect:/logout");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest httpRequestHandler) {
        if (httpRequestHandler.getRemoteUser() == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("redirect:/logout");
        }
    }

}
