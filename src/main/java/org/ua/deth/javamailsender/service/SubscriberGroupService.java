package org.ua.deth.javamailsender.service;

import org.ua.deth.javamailsender.entity.SubscriberGroup;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface SubscriberGroupService {
    void save(SubscriberGroup subscriberGroup);

    List<SubscriberGroup> getAllGroup();

    boolean deleteById(long id);

    SubscriberGroup findByName(String group);

    SubscriberGroup findById(long id);
}
