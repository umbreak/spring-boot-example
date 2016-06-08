package hello.utils;


import hello.jpa.model.InformationProviderEntity;
import hello.jpa.model.SurveyEntity;
import hello.jpa.model.TargetEntity;
import model.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProviderEntityToResponseMapping {
    private final Set<InformationProviderEntity> databaseProviders;

    public ProviderEntityToResponseMapping(Set<InformationProviderEntity> databaseProviders) {
        this.databaseProviders = databaseProviders;
    }

    public InformationProvidersResponse generate() {
        List<Identifier> providersResponse= databaseProviders.stream()
                .map(provider -> mapProviderEntityToResponse(provider)).collect(Collectors.toList());
        return new InformationProvidersResponse(providersResponse);
    }

    private Identifier mapProviderEntityToResponse(InformationProviderEntity entity){
        return new Identifier(entity.getId(),entity.getName());
    }

}
