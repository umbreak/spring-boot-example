package hello.subscription.send;


import model.SurveyException;
import hello.rest.SurveyController;
import model.ErrorResponse;
import model.MarketSurveysRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class ChannelSenderProviderFactory {
    private final SurveyController surveyController;

    @Autowired
    public ChannelSenderProviderFactory(SurveyController surveyController) {
        this.surveyController = surveyController;
    }

    public ChannelSenderProvider getChannelProvider(MarketSurveysRequest.Subscription.Channel channel){
        switch (channel){
            case API:
                return new ChannelSenderProviderAPI(surveyController);
            case Mail:
                return new ChannelSenderProviderEmail(surveyController);
            case FTP:
                return new ChannelSenderProviderFTP(surveyController);
        }
        throw new SurveyException("Channel provider for subscription not found", ErrorResponse.Error.PROVIDER_NOT_ACCESSIBLE);
    }
}
