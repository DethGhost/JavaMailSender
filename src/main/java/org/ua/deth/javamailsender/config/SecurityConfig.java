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
            auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ONE_TIME_ACCESS");
        } else {
            for (User user : users) {
                auth.inMemoryAuthentication().withUser(user.getLogin()).password(user.getPassword()).roles(user.getUserGroup().name());
            }
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/add-user/**").access("hasRole('ROLE_" + UserGroup.ADMIN + "')")
                .antMatchers("/setting/**").access("hasRole('ROLE_" + UserGroup.ADMIN + "')")
                .antMatchers("/setting/**").access("hasRole('ONE_TIME_ACCESS')")
                .and().formLogin().defaultSuccessUrl("/", false);

    }

    @Bean(name = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
