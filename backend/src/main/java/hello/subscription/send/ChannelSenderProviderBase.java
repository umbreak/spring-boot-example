package hello.subscription.send;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.jpa.model.InformationRequesterEntity;
import hello.jpa.model.SubscriptionEntity;
import hello.rest.SurveyController;
import hello.security.CustomUserDetails;
import model.MarketSurveysRequest;
import model.MarketSurveysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
public abstract class ChannelSenderProviderBase implements ChannelSenderProvider {
    private final SurveyController surveyController;
    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);


    @Autowired
    public ChannelSenderProviderBase(SurveyController surveyController) {
        this.surveyController = surveyController;
    }

    @Override
    public MarketSurveysResponse fetchSubscriptionRequest(SubscriptionEntity subscription) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = subscription.getJsonQuery();
            MarketSurveysRequest subscriptionRequest = mapper.readValue(cleanString(jsonString), MarketSurveysRequest.class);
            InformationRequesterEntity user = subscription.getOwner();
            return surveyController.search(subscriptionRequest,new CustomUserDetails(user));

        } catch (IOException e) {
            logger.warn("subscription request could not be converted to MarketSurveysRequest = " + subscription.getJsonQuery(), e);
            return null;
        }
    }
    private String cleanString(String jsonString){
        return jsonString.replace("\\\"", "\"");
    }
}
