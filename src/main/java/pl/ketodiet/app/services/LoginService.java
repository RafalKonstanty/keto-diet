package pl.ketodiet.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.ketodiet.app.model.User;
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
        List<User> userCheck = (userRepository.isUserDuplicated(name));
        return !userCheck.isEmpty();
    }


    public boolean isLoginCorrect(User user) {
        List<User> loginCheck = userRepository.findUserByNameAndPassword(user.getName(), user.getPassword());
        return !loginCheck.isEmpty();
    }

    public boolean checkSession(Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute(LOG_STATUS);
            log.info("User: " + user.getName());
            model.addAttribute("userName", user.getName());
            return true;
        } catch (NullPointerException e) {
            log.info("No user logged");
            return false;
        }
    }


}
