package hello.subscription.send;


import hello.jpa.model.InformationRequesterEntity;
import hello.jpa.model.SubscriptionEntity;
import hello.rest.SurveyController;
import model.MarketSurveysResponse;
import org.springframework.stereotype.Component;

public class ChannelSenderProviderEmail extends ChannelSenderProviderBase {

    public ChannelSenderProviderEmail(SurveyController surveyController) {
        super(surveyController);
    }

    @Override
    public boolean send(SubscriptionEntity subscription) {
        MarketSurveysResponse marketSurveysResponse = super.fetchSubscriptionRequest(subscription);
        if(marketSurveysResponse == null) return false;
        InformationRequesterEntity user = subscription.getOwner();
        //TODO: send through email the response to the user
        return true;
    }
}
