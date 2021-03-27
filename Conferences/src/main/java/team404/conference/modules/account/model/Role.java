package team404.conference.modules.account.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum Role implements GrantedAuthority {
    ADMIN, PRESENTER, LISTENER;

    public static final Collection<Role> ROLES = Collections.unmodifiableList(
            Arrays.asList(
                    ADMIN,
                    PRESENTER,
                    LISTENER
            )
    );

    @Override
    public String getAuthority() {
        return name();
    }
}
