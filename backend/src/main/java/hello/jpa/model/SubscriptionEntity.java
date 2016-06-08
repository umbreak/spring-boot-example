package hello.jpa.model;

import model.MarketSurveysRequest;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private MarketSurveysRequest.Subscription.Channel channel;

    @Enumerated(EnumType.STRING)
    private MarketSurveysRequest.Subscription.Frequency frequency;

    private Date lastTimeSent;

    private String jsonQuery;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ownerID")
    private InformationRequesterEntity owner;

    public SubscriptionEntity() {
    }

    public SubscriptionEntity(MarketSurveysRequest.Subscription.Channel channel, MarketSurveysRequest.Subscription.Frequency frequency, String jsonQuery, InformationRequesterEntity owner) {
        this.channel = channel;
        this.frequency = frequency;
        this.jsonQuery = jsonQuery;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MarketSurveysRequest.Subscription.Channel getChannel() {
        return channel;
    }

    public void setChannel(MarketSurveysRequest.Subscription.Channel channel) {
        this.channel = channel;
    }

    public MarketSurveysRequest.Subscription.Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(MarketSurveysRequest.Subscription.Frequency frequency) {
        this.frequency = frequency;
    }

    public Date getLastTimeSent() {
        return lastTimeSent;
    }

    public void setLastTimeSent(Date lastTimeSent) {
        this.lastTimeSent = lastTimeSent;
    }

    public String getJsonQuery() {
        return jsonQuery;
    }

    public void setJsonQuery(String jsonQuery) {
        this.jsonQuery = jsonQuery;
    }

    public InformationRequesterEntity getOwner() {
        return owner;
    }

    public void setOwner(InformationRequesterEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "SubscriptionEntity{" +
                "id=" + id +
                ", channel=" + channel +
                ", frequency=" + frequency +
                ", lastTimeSent=" + lastTimeSent +
                ", jsonQuery='" + jsonQuery + '\'' +
                ", owner=" + owner +
                '}';
    }
}
