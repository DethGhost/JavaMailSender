package org.ua.deth.javamailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ua.deth.javamailsender.entity.Subscriber;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

}
