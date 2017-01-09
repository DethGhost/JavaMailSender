package org.ua.deth.javamailsender.entity;

import javax.persistence.*;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Entity(name = "subscriber_group")
public class SubscriberGroup {

    @Id
    @SequenceGenerator(name = "subscriber_group_id_seq", sequenceName = "subscriber_group_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_group_id_seq")
    @Column(name = "subscriber_group_id")
    private long subscriberGroupId;

    @Column(name = "group_name")
    private String groupName;

    public SubscriberGroup() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
