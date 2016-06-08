package hello.rest;

import hello.jpa.dao.InformationProviderDAO;
import hello.jpa.model.InformationProviderEntity;
import hello.utils.ProviderEntityToResponseMapping;
import model.InformationProvidersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final InformationProviderDAO providerRepository;

    @Autowired
    public ProviderController(InformationProviderDAO providerRepository) {
        this.providerRepository = providerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public InformationProvidersResponse listAll(@AuthenticationPrincipal final UserDetails user) {
        Set<InformationProviderEntity> databaseProviders=new HashSet<>();

        List<String> userRoles=user.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", "")).collect(Collectors.toList());

        for (String userRole : userRoles) {
            Collection<InformationProviderEntity> providersByRolesName = providerRepository.findByRolesName(userRole);
            databaseProviders.addAll(providersByRolesName);
        }
        return new ProviderEntityToResponseMapping(databaseProviders).generate();
    }

}
