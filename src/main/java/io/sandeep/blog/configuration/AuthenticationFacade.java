package io.sandeep.blog.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/*
 *
 * Returns the Authentication object to get the logged in username.
 */
@Component
public class AuthenticationFacade implements IAuthenticationFacade {


    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
