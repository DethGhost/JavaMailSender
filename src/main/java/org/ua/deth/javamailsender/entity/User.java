package org.ua.deth.javamailsender.entity;

import javax.persistence.*;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Entity(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "user_id")
    private long userId;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private UserGroup userGroup;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
