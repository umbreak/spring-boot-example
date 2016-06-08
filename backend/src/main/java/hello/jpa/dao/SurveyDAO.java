package hello.jpa.dao;

import com.neovisionaries.i18n.CountryCode;
import hello.jpa.model.SurveyEntity;
import model.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface SurveyDAO extends JpaRepository<SurveyEntity, Long>, JpaSpecificationExecutor<SurveyEntity>{
}
