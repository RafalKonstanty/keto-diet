package pl.ketodiet.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.ketodiet.app.model.UserEntity;
import pl.ketodiet.app.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

import static pl.ketodiet.app.controllers.LoginController.LOG_STATUS;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkIfUserExist(String name) {
        List<UserEntity> userEntityCheck = (userRepository.isUserDuplicated(name));
        return !userEntityCheck.isEmpty();
    }


    public boolean isLoginCorrect(UserEntity userEntity) {
        List<UserEntity> loginCheck = userRepository.findUserByNameAndPassword(userEntity.getName(), userEntity.getPassword());
        return !loginCheck.isEmpty();
    }

    public boolean checkSession(Model model, HttpSession session) {
        try {
            UserEntity userEntity = (UserEntity) session.getAttribute(LOG_STATUS);
            log.info("UserEntity: " + userEntity.getName());
            model.addAttribute("userName", userEntity.getName());
            return true;
        } catch (NullPointerException e) {
            log.info("No userEntity logged");
            return false;
        }
    }


}
