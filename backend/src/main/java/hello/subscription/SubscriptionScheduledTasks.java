package hello.subscription;

import hello.jpa.dao.SubscriptionDAO;
import hello.jpa.model.SubscriptionEntity;
import hello.rest.SurveyController;
import hello.subscription.send.ChannelSenderProvider;
import hello.subscription.send.ChannelSenderProviderFactory;
import model.MarketSurveysRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
@ComponentScan
public class SubscriptionScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionScheduledTasks.class);

    private final ChannelSenderProviderFactory channelProviderCreator;
    private final SubscriptionDAO subscriptionRepository;

    @Autowired
    public SubscriptionScheduledTasks(ChannelSenderProviderFactory channelProviderCreator, SubscriptionDAO subscriptionRepository) {
        this.channelProviderCreator = channelProviderCreator;
        this.subscriptionRepository = subscriptionRepository;
    }

    //Running every 24 hours starting now
    @Scheduled(initialDelay = 0,fixedRate = 1000*60*24)
    @Transactional
    public void subscriptionTaskChecker(){
        logger.info("Start checking subcriptions");
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();

        for (SubscriptionEntity subscription : subscriptions) {
            sendToChannelSubscribed(subscription);
        }
    }

    private void sendToChannelSubscribed(SubscriptionEntity subscription){
        if(hasToBeSent(subscription)) {
            logger.info("Trying to send subscription ==" + subscription);
            ChannelSenderProvider channelProvider = channelProviderCreator.getChannelProvider(subscription.getChannel());

            boolean isSendCorrect = channelProvider.send(subscription);
            if (isSendCorrect) {
                subscription.setLastTimeSent(new Date());
                subscriptionRepository.save(subscription);
                logger.info("Subscription sent to send subscription ==" + subscription);
            }
        }
    }

    private boolean hasToBeSent(SubscriptionEntity subscription){
        FrequencyImpl frequencyImp = FrequencyImpl.fromSubscriptionFrequency(subscription.getFrequency());
        Date lastTimeSent = subscription.getLastTimeSent();
        return frequencyImp.hasTimeFromLastDatePassed(lastTimeSent);
    }
}
