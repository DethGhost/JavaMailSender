package org.ua.deth.javamailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ua.deth.javamailsender.entity.User;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from org.ua.deth.javamailsender.entity.User u where u.login= :login")
    User findByLogin(@Param("login") String login);

}
