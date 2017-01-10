package org.ua.deth.javamailsender.service;

import org.ua.deth.javamailsender.entity.User;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */

public interface UserService {
    void save(User user);

    List<User> getAllUsers();

    boolean isExist(String login);

    User findByLogin(String login);
}
