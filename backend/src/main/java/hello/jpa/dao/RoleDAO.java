package hello.jpa.dao;

import hello.jpa.model.InformationRequesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleDAO extends JpaRepository<InformationRequesterEntity, Long>{
}
