package hello.subscription.send;


import hello.jpa.model.SubscriptionEntity;
import model.MarketSurveysResponse;

public interface ChannelSenderProvider {
    MarketSurveysResponse fetchSubscriptionRequest(SubscriptionEntity subscription);
    boolean send(SubscriptionEntity subscription);
}
