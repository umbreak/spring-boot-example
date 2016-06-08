package hello.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.SurveyException;
import hello.jpa.dao.SubscriptionDAO;
import hello.jpa.model.InformationRequesterEntity;
import hello.jpa.model.SubscriptionEntity;
import model.ErrorResponse;
import model.MarketSurveysRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SubscriptionAggregator {

    private final long MAX_SUBSCRIPTIONS=3;

    private final SubscriptionDAO subscriptionRepository;

    private final UserDetails user;

    public SubscriptionAggregator(SubscriptionDAO subscriptionRepository, UserDetails user) {
        this.subscriptionRepository = subscriptionRepository;
        this.user = user;
    }

    public void subscribeUserTo(MarketSurveysRequest request) throws JsonProcessingException {
        MarketSurveysRequest.Subscription subscription=request.getSubscription();
        if(subscription == null) return;
        throwsIfMissingRequiredParams(subscription);
        throwsIfTooManySubscriptions();
        SubscriptionEntity subscriptionEntity = generateSubscriptionEntityFromRequest(request, subscription);
        subscriptionEntity.setLastTimeSent(new Date());
        subscriptionRepository.save(subscriptionEntity);
    }

    private void throwsIfMissingRequiredParams(MarketSurveysRequest.Subscription subscription){
        if(subscription.getChannel() == null || subscription.getFrequency() == null)
            throw new SurveyException("Missing subscription required parameters", ErrorResponse.Error.MISSING_REQUEST_REQUIREMENTS);
    }

    private void throwsIfTooManySubscriptions(){
        long actualSubscriptions = subscriptionRepository.countByOwnerUsername(user.getUsername());
        if(actualSubscriptions >= MAX_SUBSCRIPTIONS)
            throw new SurveyException("No more subscriptions can be added to your account. You have already " + actualSubscriptions + " and the system allows " + MAX_SUBSCRIPTIONS, ErrorResponse.Error.TOO_MANY_SUBSCRIPTIONS);
    }

    private SubscriptionEntity generateSubscriptionEntityFromRequest(MarketSurveysRequest request, MarketSurveysRequest.Subscription subscription) throws JsonProcessingException {
        String json=generateJsonStringFromRequest(request);
        return new SubscriptionEntity(subscription.getChannel(),subscription.getFrequency(),json,(InformationRequesterEntity) user);
    }

    private String generateJsonStringFromRequest(MarketSurveysRequest request) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        request.setSubscription(null);
        return mapper.writeValueAsString(request);
    }
}
