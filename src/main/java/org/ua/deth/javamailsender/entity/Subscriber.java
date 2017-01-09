package org.ua.deth.javamailsender.entity;

import javax.persistence.*;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Entity
public class Subscriber {
    @Id
    @SequenceGenerator(name = "subscriber_id_seq", sequenceName = "subscriber_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_id_seq")
    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String email;

    @ManyToOne
    private SubscriberGroup group;

    public Subscriber() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubscriberGroup getGroup() {
        return group;
    }

    public void setGroup(SubscriberGroup group) {
        this.group = group;
    }
}
