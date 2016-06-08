package hello.jpa.dao;

import hello.jpa.model.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TargetDAO extends JpaRepository<TargetEntity, Long>, JpaSpecificationExecutor<TargetEntity>{
}
