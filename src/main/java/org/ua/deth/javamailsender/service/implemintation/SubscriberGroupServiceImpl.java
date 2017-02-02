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

    private final SubscriberGroupRepository repository;

    @Autowired
    public SubscriberGroupServiceImpl(SubscriberGroupRepository repository) {
        this.repository = repository;
    }

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
        if(repository.findOne(id) != null){
            repository.delete(id);
            return;
        }else{
            return;
        }
    }

    @Override
    public SubscriberGroup findByName(String group) {
        return repository.finfByName(group);
    }

    @Override
    public SubscriberGroup findById(long id) {
        return repository.findOne(id);
    }
}
