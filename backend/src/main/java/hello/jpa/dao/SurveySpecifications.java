package hello.jpa.dao;

import com.google.common.base.Strings;
import com.neovisionaries.i18n.CountryCode;
import hello.jpa.model.InformationProviderEntity;
import hello.jpa.model.SurveyEntity;
import hello.jpa.model.SurveyEntity_;
import model.Identifier;
import model.Survey;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveySpecifications {

    public static Specification<SurveyEntity> surveyFromRequest(Survey.Request request, Identifier provider) {
        return new Specification<SurveyEntity>() {
            @Override
            public Predicate toPredicate(Root<SurveyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                SurveyRequestQuery requestQuery = new SurveyRequestQuery(root, cb);
                Predicate[] predicates=requestQuery.buildQuery(request, provider);
                return cb.and(predicates);
            }
        };
    }

    public static class SurveyRequestQuery {
        private final Root<SurveyEntity> root;
        private final CriteriaBuilder cb;

        public SurveyRequestQuery(Root<SurveyEntity> root, CriteriaBuilder cb) {
            this.root = root;
            this.cb = cb;
        }

        public Predicate[] buildQuery(Survey.Request request, Identifier provider) {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(surveyProviderID(provider));
            predicates.add(surveyWithCountry(request.getCountry()));
            predicates.add(surveyWithSubject(request.getSubject()));
            return removeNullAndCreateArray(predicates);
        }

        private Predicate[] removeNullAndCreateArray(List<Predicate> predicates){
           predicates.removeAll(Collections.singleton(null));
            return predicates.toArray(new Predicate[predicates.size()]);
        }

        private Predicate surveyWithCountry(CountryCode country) {
            if (country == null) return null;
            return cb.equal(root.get(SurveyEntity_.country), country);
        }

        private Predicate surveyProviderID(Identifier provider) {
            return cb.equal(root.get(SurveyEntity_.provider), toEntity(provider));
        }

        private Predicate surveyWithSubject(String subject) {
            if (Strings.isNullOrEmpty(subject)) return null;
            return cb.like(root.get(SurveyEntity_.subject), "%" + subject + "%");
        }

        private InformationProviderEntity toEntity(Identifier provider){
            return new InformationProviderEntity(provider.getId(),provider.getName());

        }
    }




}
