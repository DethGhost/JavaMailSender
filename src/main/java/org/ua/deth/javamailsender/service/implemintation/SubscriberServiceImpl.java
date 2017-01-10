package org.ua.deth.javamailsender.service.implemintation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.Subscriber;
import org.ua.deth.javamailsender.repository.SubscriberRepository;
import org.ua.deth.javamailsender.service.SubscriberService;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository repository;

    @Override
    public void save(Subscriber subscriber) {
        repository.saveAndFlush(subscriber);
    }

    @Override
    public List<Subscriber> getAllSubscribers() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.delete(id);
    }
}
