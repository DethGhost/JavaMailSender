package org.ua.deth.javamailsender.service.implemintation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.User;
import org.ua.deth.javamailsender.repository.UserRepository;
import org.ua.deth.javamailsender.service.UserService;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repository.saveAndFlush(user);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @Override
    public boolean isExist(String login) {
        return repository.findByLogin(login) != null?true:false;
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
