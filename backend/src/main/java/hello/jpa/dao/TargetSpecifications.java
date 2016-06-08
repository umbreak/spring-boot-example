package hello.jpa.dao;

import hello.jpa.model.TargetEntity;
import hello.jpa.model.TargetEntity_;
import model.Gender;
import model.Target;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TargetSpecifications {

    public static Specification<TargetEntity> targetsFromSurveyRequest(Target.Request targetRequest, List<Long> surveyIDs) {
        return new Specification<TargetEntity>() {
            @Override
            public Predicate toPredicate(Root<TargetEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                TargetRequestQuery requestQuery = new TargetRequestQuery(root, cb);
                Predicate[] predicates=requestQuery.buildQuery(targetRequest,surveyIDs);
                return cb.and(predicates);
            }
        };
    }

    public static class TargetRequestQuery {
        private final Root<TargetEntity> root;
        private final CriteriaBuilder cb;

        public TargetRequestQuery(Root<TargetEntity> root, CriteriaBuilder cb) {
            this.root = root;
            this.cb = cb;
        }

        public Predicate[] buildQuery(Target.Request targetRequest, List<Long> surveyIDs) {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(targetWithSurveyIDs(surveyIDs));
            if(targetRequest != null) {
                predicates.add(surveyWithTargetAgeRange(targetRequest.getAge()));
                predicates.add(surveyWithTargetGender(targetRequest.getGender()));
                predicates.add(surveyWithTargetIncomeRange(targetRequest.getIncome()));
            }
            return removeNullAndCreateArray(predicates);
        }

        private Predicate targetWithSurveyIDs(List<Long> surveyIDs){
            List<Predicate> predicates=new ArrayList<>();
            for (Long surveyID : surveyIDs) {
                predicates.add(cb.equal(root.get(TargetEntity_.surveyID), surveyID));
            }
            return cb.or(removeNullAndCreateArray(predicates));
        }

        private Predicate[] removeNullAndCreateArray(List<Predicate> predicates){
           predicates.removeAll(Collections.singleton(null));
            return predicates.toArray(new Predicate[predicates.size()]);
        }

        private Predicate surveyWithTargetGender(Gender gender) {
            if (gender == null) return null;
            return cb.equal(root.get(TargetEntity_.gender), gender);
        }

        private Predicate surveyWithTargetAgeRange(List<Integer> range) {
            if (!correctRange(range)) return null;
            return cb.between(root.get(TargetEntity_.age), range.get(0), range.get(1));
        }

        private Predicate surveyWithTargetIncomeRange(Target.Request.CurrencyRange currencyRange) {
            if (currencyRange == null) return null;
            Predicate incomeCurrencyPrecidate = cb.equal(root.get(TargetEntity_.incomeCurrency), currencyRange.getCurrency());

            List<Integer> rangeIncome = currencyRange.getRange();
            if (!correctRange(rangeIncome)) {
                return incomeCurrencyPrecidate;
            }
            return cb.and(
                    cb.between(root.get(TargetEntity_.incomeAmount), rangeIncome.get(0), rangeIncome.get(1)),
                    incomeCurrencyPrecidate);
        }

        private boolean correctRange(List range) {
            return range != null && range.size() == 2;
        }
    }


}
