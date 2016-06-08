package hello.jpa.dao;

import hello.jpa.model.InformationProviderEntity;
import hello.jpa.model.RoleEntity;
import hello.jpa.model.TargetEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface InformationProviderDAO extends JpaRepository<InformationProviderEntity, Long>{
    Collection<InformationProviderEntity> findByRolesName(String role);
}
