package org.ua.deth.javamailsender.service.implemintation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ua.deth.javamailsender.entity.SubscriberGroup;
import org.ua.deth.javamailsender.repository.SubscriberGroupRepository;
import org.ua.deth.javamailsender.service.SubscriberGroupService;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Service
public class SubscriberGroupServiceImpl implements SubscriberGroupService {

    @Autowired
    private SubscriberGroupRepository repository;

    @Override
    public void save(SubscriberGroup subscriberGroup) {
        repository.saveAndFlush(subscriberGroup);
    }

    @Override
    public List<SubscriberGroup> getAllGroup() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.delete(id);
    }
}
