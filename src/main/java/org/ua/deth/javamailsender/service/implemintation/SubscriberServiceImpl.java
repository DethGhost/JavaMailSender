package org.ua.deth.javamailsender.service.implemintation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.Subscriber;
import org.ua.deth.javamailsender.repository.SubscriberGroupRepository;
import org.ua.deth.javamailsender.repository.SubscriberRepository;
import org.ua.deth.javamailsender.service.SubscriberService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository repository;

    @Autowired
    private SubscriberGroupRepository groupRepository;

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

    @Override
    public Subscriber getSubscriberById(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Subscriber> getByGroup(long id) {
        return repository.findAll().stream().filter((subscriber) -> subscriber.getGroup().getSubscriberGroupId() == id).collect(Collectors.toList());
    }
}
