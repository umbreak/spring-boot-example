package hello.rest;

import model.SurveyException;
import hello.jpa.dao.InformationProviderDAO;
import hello.jpa.model.InformationProviderEntity;
import hello.jpa.model.RoleEntity;
import model.ErrorResponse;
import model.Identifier;
import model.MarketSurveysRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Component
public class SurveyRequestVerifier {

    private final InformationProviderDAO providerRepository;

    @Autowired
    public SurveyRequestVerifier(InformationProviderDAO providerRepository) {
        this.providerRepository = providerRepository;
    }

    public void checkSurveyRequest(MarketSurveysRequest request, UserDetails user) {
        if(request.getSurvey() == null){
            throw new SurveyException("Survey is missing", ErrorResponse.Error.MISSING_REQUEST_REQUIREMENTS);
        }
        if(request.getProvider() == null){
            throw new SurveyException("provider is missing", ErrorResponse.Error.MISSING_REQUEST_REQUIREMENTS);
        }
        if(!roleExistForProvider(request.getProvider(),user)){
            throw new SurveyException("Provider missing or role not allowed for this provider",ErrorResponse.Error.PROVIDER_NOT_ACCESSIBLE);
        }
    }

    private boolean roleExistForProvider(Identifier provider, UserDetails user){
        InformationProviderEntity dbProvider = providerRepository.findOne(provider.getId());
        if(dbProvider == null || !Objects.equals(dbProvider.getName(), provider.getName())){
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return isSomeRoleForProvider(dbProvider.getRoles(),authorities);
    }

    private boolean isSomeRoleForProvider(Set<RoleEntity> roles, Collection<? extends GrantedAuthority> authorities){
        for (GrantedAuthority authority : authorities)
            for (RoleEntity role : roles)
                if(Objects.equals(role.getName(), authority.getAuthority().replace("ROLE_", "")))
                    return true;
        return false;
    }
}
