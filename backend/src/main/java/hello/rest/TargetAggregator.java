package hello.rest;

import hello.jpa.dao.TargetDAO;
import hello.jpa.model.SurveyEntity;
import hello.jpa.model.TargetEntity;
import model.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hello.jpa.dao.TargetSpecifications.targetsFromSurveyRequest;
import static org.springframework.data.jpa.domain.Specifications.where;

@Component
public class TargetAggregator {

    private final TargetDAO targetRepository;

    @Autowired
    public TargetAggregator(TargetDAO targetRepository) {
        this.targetRepository = targetRepository;
    }

    public List<SurveyEntity> addRequestedTargetsToSurveys(Target.Request targetRequest, List<SurveyEntity> surveys){
        List<Long> surveysIds=surveys.stream().map(survey -> survey.getId()).collect(Collectors.toList());
        List<TargetEntity> targets = targetRepository
                .findAll(where(targetsFromSurveyRequest(targetRequest,surveysIds)));
        Map<Long, List<TargetEntity>> targetsMap=targetEntityToMap(targets);
        for (SurveyEntity survey : surveys) {
            List<TargetEntity> targetEntities = targetsMap.get(survey.getId());
            if(targetEntities == null)
                targetEntities=new ArrayList<>();
            survey.setTarget(targetEntities);
        }
        return surveys;
    }

    private Map<Long, List<TargetEntity>> targetEntityToMap(List<TargetEntity> targets){

        Map<Long, List<TargetEntity>> map=new HashMap<>();
        for (TargetEntity target : targets) {
            List<TargetEntity> list=map.get(target.getSurveyID());
            if(list == null) {
                list = new ArrayList<>();
                map.put(target.getSurveyID(),list);
            }
            list.add(target);
        }
        return map;
    }


}
