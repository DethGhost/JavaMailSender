package org.ua.deth.javamailsender.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.ua.deth.javamailsender.entity.User;
import org.ua.deth.javamailsender.entity.UserGroup;
import org.ua.deth.javamailsender.service.UserService;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<User> users = service.getAllUsers();
        // If in DB no users you can access to "add-user" using login "admin" and password "admin"
        if (users.isEmpty()) {
            auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        } else {
            for (User user : users) {
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser(user.getLogin()).password(user.getPassword()).roles(user.getUserGroup().name());
            }
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/setting/**").access("hasRole('ROLE_" + UserGroup.ADMIN + "')")
                .antMatchers("/subscribers/**").access("hasRole('ROLE_" + UserGroup.ADMIN + "') or hasRole('ROLE_" + UserGroup.ONLY_ADD_SUBSCRIBERS + "')")
                .antMatchers("/setting/add-user/**").access("hasRole('ROLE_" + UserGroup.ADMIN + "') or hasRole('ROLE_" + UserGroup.USER + "')")
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/", false)
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

    }

    private String[] roles(String role) {
        if (role.equals("adminAndOnlySub")) {
            return new String[]{UserGroup.ADMIN.name(), UserGroup.ONLY_ADD_SUBSCRIBERS.name()};
        } else if (role.equals("adminAndUser")) {
            return new String[]{UserGroup.ADMIN.name(), UserGroup.USER.name()};
        } else if (role.equals("adminAndUserAndOnlySub")) {
            return new String[]{UserGroup.ADMIN.name(), UserGroup.USER.name(), UserGroup.ONLY_ADD_SUBSCRIBERS.name()};
        } else {
            return new String[]{UserGroup.ADMIN.name()};
        }
    }

    @Bean(name = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
