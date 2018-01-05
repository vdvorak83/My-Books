package ru.itis.security.details;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.models.User;
import ru.itis.security.enums.State;

import java.util.Collection;
import java.util.Collections;

// класс, который помогает спрингу понять, как устроена ваша сущность для авторизации
public class UserDetailsImpl implements UserDetails {

    private User user;

    UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        //return this.user.getState().equals(State.CONFIRMED); //TODO (хули с этим делать???????)
    }

    public User getUser() {
        return user;
    }
}
