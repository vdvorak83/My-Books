package ru.itis.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Collection;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> userOptional = usersRepository.findOneByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            System.out.println("FIRST CONDITION");

            if (!passwordEncoder.matches(password, user.getHashPassword())) {
                System.out.println("SECOND CONDITION");
                if (passwordEncoder.matches(password, user.getHashTempPassword())) {
                    System.out.println("THIRD CONDITION");
                    user.setHashTempPassword(null);
                    usersRepository.save(user);
                }
                else {
                    throw new BadCredentialsException("Wrong password or login");
                }
            }
        }
        else {
            throw new BadCredentialsException("Wrong password or login");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
