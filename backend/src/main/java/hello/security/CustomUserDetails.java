package hello.security;


import hello.jpa.model.InformationRequesterEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomUserDetails extends InformationRequesterEntity implements UserDetails{
    public CustomUserDetails(InformationRequesterEntity user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] rolesString= getRoles().stream().map(role -> role.getName()).toArray(size -> new String[size]);
        return AuthorityUtils.createAuthorityList(rolesString);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
