package pl.ketodiet.app.services;

import org.springframework.stereotype.Service;
import pl.ketodiet.app.model.User;

import javax.servlet.http.HttpSession;

import static pl.ketodiet.app.controllers.LoginController.LOG_STATUS;

@Service
public class UserService {
    public User getSessionUser(HttpSession session) {
        return (User) session.getAttribute(LOG_STATUS);
    }
}
