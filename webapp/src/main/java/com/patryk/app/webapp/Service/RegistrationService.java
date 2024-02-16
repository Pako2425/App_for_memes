package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor

public class RegistrationService {
    private final UsersRepository usersRepository;
    private final UserService userService;

    public RegistrationDataStatus registerDataCheck(RegistrationDAO registrationDAO) {
        List<User> users = usersRepository.findAllByNameOrEmail(registrationDAO.getName(), registrationDAO.getEmail());

        if(users.isEmpty()) {
            if(registrationDAO.getPassword().equals(registrationDAO.getRepeatedPassword())) {
                return RegistrationDataStatus.SUCCESS;
            }
            else {
                return RegistrationDataStatus.PASSWORD_NOT_CORRECT;
            }
        }
        else {
            boolean emailExist = users
                    .stream()
                    .anyMatch(user -> user.getEmail().equals(registrationDAO.getEmail()));
            boolean nameExist = users
                    .stream()
                    .anyMatch(user -> user.getUsername().equals(registrationDAO.getName()));

            if(emailExist) {
                return RegistrationDataStatus.EMAIL_ALREADY_EXIST;
            }
            else if(nameExist) {
                return RegistrationDataStatus.NAME_ALREADY_EXIST;
            }
            else {
                return RegistrationDataStatus.SOMETHING_WENT_WRONG;
            }
        }
    }

    public RegistrationDataStatus register(RegistrationDAO registrationDAO) {
        RegistrationDataStatus registrationDataStatus = registerDataCheck(registrationDAO);
        if(registrationDataStatus == RegistrationDataStatus.SUCCESS) {
            userService.save(registrationDAO);
        }
        return registrationDataStatus;
    }
}
