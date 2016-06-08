package hello.jpa.dao;

import hello.jpa.model.InformationRequesterEntity;
import hello.jpa.model.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface SubscriptionDAO extends JpaRepository<SubscriptionEntity, Long>{
    Long countByOwnerUsername(String username);
}
