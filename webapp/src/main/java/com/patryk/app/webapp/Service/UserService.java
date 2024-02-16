package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.UserRole;
import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final String USER_NOT_FOUND_MSG = "user with name %s not found.";
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER;

    public User save(RegistrationDAO registrationDAO) throws IllegalStateException {
        User user = new User(registrationDAO.getName(),
                registrationDAO.getEmail(),
                B_CRYPT_PASSWORD_ENCODER.encode(registrationDAO.getPassword()),
                UserRole.ROLE_USER);

        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }
}
