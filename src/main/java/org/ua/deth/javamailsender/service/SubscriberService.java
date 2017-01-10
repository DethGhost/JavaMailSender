package org.ua.deth.javamailsender.service;

import org.ua.deth.javamailsender.entity.Subscriber;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public interface SubscriberService {

    void save(Subscriber subscriber);

    List<Subscriber> getAllSubscribers();

    void deleteById(long id);

}
