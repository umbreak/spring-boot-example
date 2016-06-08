package hello.utils;


import hello.jpa.model.SurveyEntity;
import hello.jpa.model.TargetEntity;
import model.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class SurveyEntityToResponseMapping {
    private final Page<SurveyEntity> surveyEntities;
    private final MarketSurveysRequest request;

    public SurveyEntityToResponseMapping(MarketSurveysRequest request, Page<SurveyEntity> surveyEntities) {
        this.request = request;
        this.surveyEntities = surveyEntities;
    }

    public MarketSurveysResponse generate() {
                Pagination.Response pagination = mapPaginationDbtoPaginationResponse(surveyEntities);
        List<Survey.Response> responseSurveys = mapSurveyEntitiesToResponse();
        return new MarketSurveysResponse(pagination, request.getProvider(), responseSurveys);
    }

    private Pagination.Response mapPaginationDbtoPaginationResponse(Page page){
        return new Pagination.Response(page.getNumber()+1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }



    private List<Survey.Response> mapSurveyEntitiesToResponse(){
        return surveyEntities.getContent().stream()
                .map(surveyEntity ->mapSurveyEntityToResponse(surveyEntity)).collect(Collectors.toList());
    }

    private Survey.Response mapSurveyEntityToResponse(SurveyEntity entity){
        List<Target.Response> targetsResponse = mapTargetEntitiesToResponse(entity.getTarget());

        return new Survey.Response(entity.getSubject(),targetsResponse,entity.getDate());
    }

    private List<Target.Response> mapTargetEntitiesToResponse(List<TargetEntity> entities){
        return entities.stream()
                .map(targetEntity -> mapTargetEntityToResponse(targetEntity)).collect(Collectors.toList());
    }

    private Target.Response mapTargetEntityToResponse(TargetEntity entity){
        return new Target.Response.Builder()
                .name(entity.getName())
                .age(entity.getAge())
                .gender(entity.getGender())
                .surname(entity.getSurname())
                .income(new Currency(entity.getIncomeCurrency(),entity.getIncomeAmount())).build();
    }
}
