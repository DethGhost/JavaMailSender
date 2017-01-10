package org.ua.deth.javamailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ua.deth.javamailsender.entity.SubscriberGroup;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Repository
public interface SubscriberGroupRepository extends JpaRepository<SubscriberGroup, Long> {

    @Query("select g from org.ua.deth.javamailsender.entity.SubscriberGroup g where g.groupName= :groupName")
    SubscriberGroup finfByName(@Param("groupName") String group);
}
