package hello.jpa.dao;

import hello.jpa.model.InformationRequesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InformationRequesterDAO extends JpaRepository<InformationRequesterEntity, Long>{
    Optional<InformationRequesterEntity> findByUsername(String username);
}
