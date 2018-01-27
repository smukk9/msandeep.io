package io.sandeep.blog.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*
    * TO-DO: Implement boolean methods later.
    * Check for null values on the getAuthoritiess


 */

public class CustomUserDetails extends User implements UserDetails{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public CustomUserDetails(final User users){
        super(users);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

     final List<GrantedAuthority> authorities = new ArrayList<>();
     logger.info("Display user roles: {}",this.getRoles());
     for (final Role role : this.getRoles()){

         authorities.add(new SimpleGrantedAuthority(role.getRole()));
     }
     return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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
