package hello.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import hello.jpa.dao.SubscriptionDAO;
import hello.jpa.dao.SurveyDAO;
import hello.jpa.dao.TargetDAO;
import hello.jpa.model.SurveyEntity;
import hello.utils.SurveyEntityToResponseMapping;
import hello.utils.PaginationUtils;
import model.MarketSurveysRequest;
import model.MarketSurveysResponse;
import model.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static hello.jpa.dao.SurveySpecifications.surveyFromRequest;

import static org.springframework.data.jpa.domain.Specifications.where;

@RestController
@RequestMapping("/survey/")
public class SurveyController {

    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

    private final SurveyDAO surveyRepository;
    private final SubscriptionDAO subscriptionRepository;
    private final SurveyRequestVerifier requestVerifier;
    private final TargetAggregator targetAggregator;

    @Autowired
    public SurveyController(SurveyDAO surveyRepository, SubscriptionDAO subscriptionRepository, SurveyRequestVerifier requestVerifier, TargetAggregator targetAggregator) {
        this.surveyRepository = surveyRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.requestVerifier = requestVerifier;
        this.targetAggregator = targetAggregator;
    }



    @RequestMapping(value = "searches",method = RequestMethod.POST)
    public MarketSurveysResponse search(@RequestBody MarketSurveysRequest request, @AuthenticationPrincipal final UserDetails user) {
        requestVerifier.checkSurveyRequest(request, user);

        Pageable pagination = PaginationUtils.createPageRequest(request);

        //Search for surveys
        Page<SurveyEntity> surveys = surveyRepository
                .findAll(where(surveyFromRequest(request.getSurvey(), request.getProvider())), pagination);

        if(surveys == null || surveys.getTotalElements() == 0){
            return MarketSurveysResponse.empty(request.getProvider(), PaginationUtils.DEFAULT_PAGE_SIZE);
        }

        //Attach filtered targets to surveys
        Target.Request targetRequest = request.getSurvey().getTarget();
        targetAggregator.addRequestedTargetsToSurveys(targetRequest, surveys.getContent());

        tryToAddSubscription(user,request);

        //Map response
        return new SurveyEntityToResponseMapping(request,surveys).generate();
    }

    private void tryToAddSubscription(final UserDetails user, MarketSurveysRequest request){
        try {
            new SubscriptionAggregator(subscriptionRepository,user).subscribeUserTo(request);
        } catch (JsonProcessingException e) {
            logger.warn("Subscription not added because of wrong json unmarshalling of request =" + request,e);
        }
    }


}
