package org.ua.deth.javamailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ua.deth.javamailsender.entity.Subscriber;

import java.util.List;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

    @Query("select s from org.ua.deth.javamailsender.entity.Subscriber where s.group.subscriberGroupId = :id")
    List<Subscriber> getAllInGroup(@Param("id") long id);
}
