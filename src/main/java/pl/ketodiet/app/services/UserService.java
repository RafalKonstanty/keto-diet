package pl.ketodiet.app.services;

import org.springframework.stereotype.Service;
import pl.ketodiet.app.model.UserEntity;

import javax.servlet.http.HttpSession;

import static pl.ketodiet.app.controllers.LoginController.LOG_STATUS;

@Service
public class UserService {
    public UserEntity getSessionUser(HttpSession session) {
        return (UserEntity) session.getAttribute(LOG_STATUS);
    }
}
