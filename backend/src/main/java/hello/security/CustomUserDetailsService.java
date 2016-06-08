package hello.security;

import hello.jpa.dao.InformationRequesterDAO;
import hello.jpa.model.InformationRequesterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final InformationRequesterDAO userRepository;

    @Autowired
    public CustomUserDetailsService(InformationRequesterDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<InformationRequesterEntity> userOption = userRepository.findByUsername(username);
        if (!userOption.isPresent() || userOption.get() == null)
            throw new UsernameNotFoundException("No user present with username: " + username);
        InformationRequesterEntity userEntity = userOption.get();
        return new CustomUserDetails(userEntity);
    }
}